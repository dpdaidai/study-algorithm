package top.dpdaidai.algorithm.elementary;

/**
 * 冒泡排序
 * 时间复杂度 : O(n²) ,额外空间复杂度O(1)
 * 测试数据 :
 * 数组长度 5000
 * 数大小 0-5000
 * 运行次数 2000
 * <p>
 * 结论数据 :
 * 运行机器 : MBP2015 15寸
 * 排序总耗时 : 55028 ms
 * 平均排序耗时 : 27 ms
 *
 * @Author chenpantao
 * @Date 1/13/21 11:56 AM
 * @Version 1.0
 */
public class Note03_BubbleSort {

    public static void main(String[] args) {
        int testTime = 2000;
        int maxSize = 5000;
        int maxValue = 5000;

//        int[] array = generateSpecifyLengthRandomArray(maxSize, maxValue);
//        int[] copyArray = copyArray(array);
//        printArray(array);
//        bubbleSort(array);
//        printArray(array);
//
//        comparator(copyArray);
//        printArray(copyArray);
//        boolean equal = isEqual(array, copyArray);
//        System.out.println(equal);

        long timeConsuming = 0;

        for (int i = 0; i < testTime; i++) {
            //生成随机数组
            int[] array = ArrayUtil.generateSpecifyLengthRandomArray(maxSize, maxValue);
            int[] copyArray = ArrayUtil.copyArray(array);

            //冒牌排序
            long start = System.currentTimeMillis();
            bubbleSort(array);
            long end = System.currentTimeMillis();

            //数组自带API排序
            ArrayUtil.comparator(copyArray);

            //累积每次排序耗时
//            System.out.println("耗时 : " + (end - start));
            timeConsuming += (end - start);

            //判断排序是否正确
            if (!ArrayUtil.isEqual(array, copyArray)) {
                System.out.println("排序有误");
                break;
            }

        }

        System.out.println("排序" + testTime + " 次 , 耗时 : " + timeConsuming);
        System.out.println("平均耗时 : " + timeConsuming / testTime);

    }

    public static void bubbleSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = array.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int swap = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = swap;
                }

            }

        }

    }


}
