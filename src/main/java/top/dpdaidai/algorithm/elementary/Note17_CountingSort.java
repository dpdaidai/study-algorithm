package top.dpdaidai.algorithm.elementary;

/**
 *
 * 计数排序
 * 当输入的元素是 n 个 0 到 k 之间的整数时，它的运行时间是 Θ(n + k)。作为一种线性时间复杂度的排序, 计数排序要求输入的数据必须是有确定范围的整数.
 * 简单计数排序转载自 https://www.runoob.com/w3cnote/counting-sort.html
 * 稳定计数排序转载自 https://cloud.tencent.com/developer/article/1356053
 *
 * 计数排序特点 :
 *  +   计数排序不是比较排序, 排序的速度快于任何比较排序算法
 *  +   计数排序的核心在于将输入的数据值转化为键存储在额外开辟的数组空间中.
 *  +   由于用来计数的数组C的长度取决于待排序数组中数据的范围（等于待排序数组的最大值与最小值的差加上1），这使得计数排序对于数据范围很大的数组，需要大量时间和内存。
 *          例如：计数排序是用来排序0到100之间的数字的最好的算法，但是它不适合按字母顺序排序人名。但是，计数排序可以用在基数排序中的算法来排序数据范围很大的数组。
 *
 * @Author chenpantao
 * @Date 2/4/21 11:02 AM
 * @Version 1.0
 */
public class Note17_CountingSort implements SortService {

    public static final String NAME = "计数排序";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void sort(int[] array) {
        stabilityCountingSort(array);
    }

    /**
     * 简单计数排序
     * 1    找出待排序的数组中最大和最小的元素
     * 2    利用辅助数组, 统计数组中每个值为i的元素出现的次数
     * 3    填充目标数组
     */
//    public static void countingSort(int[] array) {
//        if (array == null || array.length < 2) return;
//
//        //1  找出原数组中最大和最小数
//        int min = array[0];
//        int max = array[0];
//
//        for (int i = 0; i < array.length; i++) {
//            min = array[i] < min ? array[i] : min;
//            max = array[i] > max ? array[i] : max;
//        }
//
//        //2 建立辅助数组, 利用辅助数组, 统计原数组每个数出现的次数
//        int[] helpArray = new int[max - min + 1];
//
//        for (int i = 0; i < array.length; i++) {
//            helpArray[array[i] - min]++;
//        }
//
//        //3 填充目标数组
//        int index = 0;
//        for (int i = 0; i < helpArray.length; i++) {
//            while (helpArray[i] > 0) {
//                array[index] = i + min;
//                index++;
//                helpArray[i]--;
//            }
//        }
//
//    }

    /**
     * 稳定计数排序
     * 1    找出待排序的数组A中最大和最小的元素
     * 2    利用辅助数组helpArray, 统计数组中每个值为i的元素出现的次数
     * 3    增加求和数组, 实际上是记录每个数当前下标的数组sumArray, 对helpArray数组所有的计数累加, 得到数组A中每个数排序后的下标
     * 4    反向填充目标数组, 保证排序稳定性
     * @param array
     */
    public static void stabilityCountingSort(int[] array) {
        if (array == null || array.length < 2) return;

        //使用拷贝数组记录原数组顺序
        int[] copyArray = ArrayUtil.copyArray(array);

        //1  找出原数组A中最大和最小数
        int min = copyArray[0];
        int max = copyArray[0];

        for (int i = 0; i < copyArray.length; i++) {
            min = copyArray[i] < min ? copyArray[i] : min;
            max = copyArray[i] > max ? copyArray[i] : max;
        }

        //2 建立辅助数组helpArray 利用辅助数组, 统计原数组每个数出现的次数
        int[] helpArray = new int[max - min + 1];

        for (int i = 0; i < copyArray.length; i++) {
            helpArray[copyArray[i] - min]++;
        }

        //3 增加记录下标的求和数组sumArray
        int[] sumArray = new int[max - min + 1];
        sumArray[0] = helpArray[0];
        for (int i = 1; i < sumArray.length; i++) {
            sumArray[i] = sumArray[i - 1] + helpArray[i];
        }

        //4 反向填充目标数组, 保证排序稳定性
        for (int i = copyArray.length - 1; i >= 0; i--) {

            //找到copyArray中的数 , 在sumArray中的下标
            int index = copyArray[i] - min;

            //sumArray中记录了每个数排序后的位置, 将copyArray[i] 放入元数组中对应的位置
            array[sumArray[index] - 1] = copyArray[i];

            //当某些数有重复时, 被取出的数会导致sumArray[index]--
            sumArray[index]--;
        }

    }
}
