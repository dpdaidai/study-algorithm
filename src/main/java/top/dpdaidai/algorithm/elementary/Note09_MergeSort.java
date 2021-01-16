package top.dpdaidai.algorithm.elementary;

/**
 * 归并排序
 * <p>
 * 时间复杂度  T(n) = 2T(n/2) + O(n) = O(N * logN)
 * 远高于 O(n²)
 * 额外空间复杂度来源于辅助数组 helpArray , 大小为 O(n)
 * <p>
 * 测试数据 :
 * 数组长度 10000
 * 数大小 -5000 - +5000
 * 运行次数 1000
 * <p>
 * 结论数据 :
 * 运行机器 : MBP2015 15寸
 * 排序总耗时 : 1200 ms
 * 平均排序耗时 : 1 ms
 *
 * @Author chenpantao
 * @Date 1/16/21 10:04 AM
 * @Version 1.0
 */
public class Note09_MergeSort implements SortService {

    public static final String NAME = "归并排序";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void sort(int[] array) {
        mergeSort(array, 0, array.length - 1);

    }

    // 递归分治
    public static void mergeSort(int[] array, int left, int right) {

        if (left == right) return;

//        int middle = (left + right) / 2;         // right + left 有可能导致溢出
//        int middle = left + (right - left) / 2;    // 防止溢出
        int middle = left + ((right - left) >> 1);

        //1  使左半数组有序
        mergeSort(array, left, middle);               // T(n/2)
        //2  使右半数组有序
        mergeSort(array, middle + 1, right);     // T(n/2)
        //3  将左右分别有序的数组合并
        merge(array, left, middle, right);            // O(n)


    }


    //一个数组内 , left - middle 有序, middle - right 有序 , 实现left - right之间的排序
    public static void merge(int[] array, int left, int middle, int right) {

        //辅助数组保存排序后的数据
        int[] helpArray = new int[right - left + 1];

        //左半数组指针起点
        int p1 = left;
        //右半数组指针起来
        int p2 = middle + 1;
        //helpArray数组指针起点
        int i = 0;

        //挨个比对左右两边数据的大小 , 让后按升序放入helpArray . 直到两边数组中的一方越界
        while (p1 <= middle && p2 <= right) {
            helpArray[i++] = array[p1] < array[p2] ? array[p1++] : array[p2++];
        }

        //将未越界的数组中的剩余有序数据拷贝到helpArray剩余的位置中
        while (p1 <= middle) {
            helpArray[i++] = array[p1++];
        }

        while (p2 <= right) {
            helpArray[i++] = array[p2++];
        }

        //将helpArray中的数组放回原数组中
        for (int j = 0; j < helpArray.length; j++) {
            array[left + j] = helpArray[j];
        }

    }


}
