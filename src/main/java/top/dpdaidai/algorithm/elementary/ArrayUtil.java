package top.dpdaidai.algorithm.elementary;

import java.util.Arrays;

/**
 * @Author chenpantao
 * @Date 1/14/21 11:55 AM
 * @Version 1.0
 */
public class ArrayUtil {

    public static void swap(int[] array, int i, int j) {
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }

    //数组按升序排列
    public static void sort(int[] array) {
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
        return generateRandomArray(maxValue, array);

    }

    // 生成指定长度随机数组
    public static int[] generateSpecifyLengthRandomArray(int length, int maxValue) {
        int[] array = new int[length];
        return generateRandomArray(maxValue, array);
    }

    private static int[] generateRandomArray(int maxValue, int[] array) {
        for (int i = 0; i < array.length; i++) {
//            array[i] = (int) ((maxValue + 1) * Math.random());  // Math.random() : 左闭右开 , 包含0, 不包含1 . [0,1)
            array[i] = (int) ((maxValue + 1) * Math.random() - maxValue * Math.random());
        }

        return array;
    }

    public static int getMax(int[] array) {
        if (array == null || array.length == 0){
            throw new RuntimeException("数组不能为空");
        }

        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        return max;
    }

}
