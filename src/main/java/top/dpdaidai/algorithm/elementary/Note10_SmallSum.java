package top.dpdaidai.algorithm.elementary;

/**
 * 求小和
 * 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。
 * 求一个数组 的小和。 例子： [1,3,4,2,5]
 * 1左边比1小的数，没有；
 * 3左边比3小的数，1；
 * 4左边比4小的数，1、3；
 * 2左边比2小的数，1；
 * 5左边比5小的数，1、3、4、2；
 * 所以小和为1+1+3+1+1+3+4+2=16
 * <p>
 * 简单解法 :
 * 双重遍历 , 每次遍历当前数据下标前面的所有数 , 挨个累加
 * <p>
 * 归并解法 :
 * 1  将数据分治, 每一组内部求一次小和 .
 * 2  将组内数据排序
 * 3  两组合并时, 挨个求有多少个右边组内得数比左边的数大
 *
 * @Author chenpantao
 * @Date 1/16/21 12:24 PM
 * @Version 1.0
 */

public class Note10_SmallSum {

    public static final int maxSize = 100000;
    public static final int maxValue = 1000;

    public static void main(String[] args) {

        int[] array = ArrayUtil.generateSpecifyLengthRandomArray(maxSize, maxValue);

        long start = System.currentTimeMillis();
        int smallSum = smallSum(array);
        long middle = System.currentTimeMillis();
        int sumByMergeProcess = smallSumByMergeProcess(array, 0, array.length - 1);
        long end = System.currentTimeMillis();

        System.out.println(smallSum);
        System.out.println(sumByMergeProcess);
        if (smallSum != sumByMergeProcess) {
            System.err.println("算法有误");
        }

        System.out.println("遍历求小和, 耗时 : " + (middle - start));

        System.out.println("归并求小和, 耗时 : " + (end - middle));
//        遍历求小和, 耗时 : 5673
//        归并求小和, 耗时 : 30

    }

    public static int smallSum(int[] array) {

        if (array == null || array.length == 0) {
            throw new RuntimeException("数组不能为空");
        }

        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i] > array[j]) {
                    sum += array[j];
                }
            }
        }

        return sum;
    }

    public static int smallSumByMergeProcess(int[] array, int left, int right) {

        if (array == null || array.length == 0) {
            throw new RuntimeException("数组不能为空");
        }

        if (left == right) return 0;

        int middle = left + ((right - left) >> 1);

        int leftSmallSum = smallSumByMergeProcess(array, left, middle);
        int rightSmallSum = smallSumByMergeProcess(array, middle + 1, right);
        int groupSmallSum = smallSumByMergeSort(array, left, middle, right);


        return leftSmallSum + rightSmallSum + groupSmallSum;

    }


    public static int smallSumByMergeSort(int[] array, int left, int middle, int right) {

        if (left == right) return 0;
        int smallSum = 0;

        int[] helpArray = new int[right - left + 1];

        int p1 = left;

        int p2 = middle + 1;


        int i = 0;

        while (p1 <= middle && p2 <= right) {
            smallSum += (array[p1] < array[p2] ? array[p1] * (right - p2 + 1) : 0);
            helpArray[i++] = array[p1] < array[p2] ? array[p1++] : array[p2++];
        }

        while (p1 <= middle) {
            helpArray[i++] = array[p1++];
        }
        while (p2 <= right) {
            helpArray[i++] = array[p2++];
        }

        for (int j = 0; j < helpArray.length; j++) {
            array[left + j] = helpArray[j];
        }

        return smallSum;

    }


}
