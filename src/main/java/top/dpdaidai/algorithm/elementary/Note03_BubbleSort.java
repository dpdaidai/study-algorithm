package top.dpdaidai.algorithm.elementary;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * 冒泡排序
 * 测试数据 :
 * 数组长度 5000
 * 数大小 0-5000
 * 运行次数 2000
 * <p>
 * 结论数据 :
 * 运行机器 : MBP2015 15寸
 * 排序总耗时 : 32738 ms
 * 平均排序耗时 : 16 ms
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

//        int[] array = generateSpecifyLenthRandomArray(maxSize, maxValue);
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
            int[] array = generateSpecifyLenthRandomArray(maxSize, maxValue);
            int[] copyArray = copyArray(array);

            //冒牌排序
            long start = System.currentTimeMillis();
            bubbleSort(array);
            long end = System.currentTimeMillis();

            //数组自带API排序
            comparator(copyArray);

            //累积每次排序耗时
//            System.out.println("耗时 : " + (end - start));
            timeConsuming += (end - start);

            //判断排序是否正确
            if (!isEqual(array, copyArray)) {
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
                if (array[i] < array[j]) {
                    int swap = array[i];
                    array[i] = array[j];
                    array[j] = swap;
                }

            }

        }

    }

    public static void swap(int[] array, int i, int j) {
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }

    //数组按升序排列
    public static void comparator(int[] array) {
        Arrays.sort(array);
    }

    //拷贝数组
    public static int[] copyArray(int[] array) {

//        int[] ints = Arrays.copyOf(array, array.length);

        if (array == null) {
            return null;
        }

        int[] newArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }

        return newArray;
    }

    //比对两个数组是否一致
    public static boolean isEqual(int[] array, int[] arrayCopy) {
//        boolean equals = Arrays.equals(array, arrayCopy);

        if (array == null && arrayCopy == null) {
            return true;
        }

        if ((array == null && arrayCopy != null) || (array != null && arrayCopy == null)) {
            return false;
        }

        if (array.length != arrayCopy.length) {
            return false;
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] != arrayCopy[i]) {
                return false;
            }
        }

        return true;

    }

    //打印数组
    public static void printArray(int[] array) {
        if (array == null) return;

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    // 生成随机长度 随机数组
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] array = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) ((maxValue + 1) * Math.random() - maxValue * Math.random());
        }

        return array;

    }

    // 生成指定长度随机数组
    public static int[] generateSpecifyLenthRandomArray(int length, int maxValue) {
        int[] array = new int[length];

        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (maxValue * Math.random());
        }

        return array;
    }

}
