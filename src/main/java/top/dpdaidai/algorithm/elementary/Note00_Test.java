package top.dpdaidai.algorithm.elementary;

/**
 * 对数器的使用 : 大样本测试准备
 * <p>
 * 1    生成随机数组
 * 2    拷贝该随机数组
 * 3    使用自写的排序方法对原数组排序
 * 4    使用绝对正确的排序方法对拷贝数组排序
 * 5    比对两种排序后的结果是否一致
 * 6    记录自写排序方法的总时间和平均消耗时间
 * 7    比对不同排序方法的耗时
 *
 * @Author chenpantao
 * @Date 1/14/21 12:08 PM
 * @Version 1.0
 */
public class Note00_Test {

    public static final int testTime = 1000;
    public static final int maxSize = 10000;
    public static final int maxValue = 10000;

    public static void main(String[] args) {

        Note06_StandardSort note06_standardSort = new Note06_StandardSort();
        Note00_Test.sortTest(note06_standardSort);

//        Note03_BubbleSort note03_bubbleSort = new Note03_BubbleSort();
//        Note00_Test.sortTest(note03_bubbleSort);
//
//        Note04_SelectionSort note04_selectionSort = new Note04_SelectionSort();
//        Note00_Test.sortTest(note04_selectionSort);
//
//        Note05_InsertionSort note05_insertionSort = new Note05_InsertionSort();
//        Note00_Test.sortTest(note05_insertionSort);

//        Note09_MergeSort note09_mergeSort = new Note09_MergeSort();
//        Note00_Test.sortTest(note09_mergeSort);

//        Note12_QuickSort note12_quickSort = new Note12_QuickSort();
//        Note00_Test.sortTest(note12_quickSort);
//
//        Note13_HeapSort note13_heapSort = new Note13_HeapSort();
//        Note00_Test.sortTest(note13_heapSort);

        Note17_CountingSort note17_countingSort = new Note17_CountingSort();
        Note00_Test.sortTest(note17_countingSort);

        Note18_BucketSort note18_bucketSort = new Note18_BucketSort();
        Note00_Test.sortTest(note18_bucketSort);


        //1
//        public static final int testTime = 2000;
//        public static final int maxSize = 5000;
//        public static final int maxValue = 5000;
//        标准排序总耗时 : 551
//        标准排序平均耗时 : 0
//        冒泡排序2000 次 , 耗时 : 55765
//        平均耗时 : 27
//        选择排序2000 次 , 耗时 : 15131
//        平均耗时 : 7
//        插入排序2000 次 , 耗时 : 27733
//        平均耗时 : 13
//        归并排序2000 次 , 耗时 : 1224
//        平均耗时 : 0
//        快速排序2000 次 , 耗时 : 837
//        平均耗时 : 0
//        堆排序2000 次 , 耗时 : 515
//        平均耗时 : 0
//        计数排序2000 次 , 耗时 : 129
//        平均耗时 : 0
//        桶排序2000 次 , 耗时 : 737 ms
//        平均耗时 : 0ms

        //2
//        public static final int testTime = 1000;
//        public static final int maxSize = 10000;
//        public static final int maxValue = 10000;
//        标准排序总耗时 : 557
//        标准排序平均耗时 : 0
//        冒泡排序1000 次 , 耗时 : 124848
//        平均耗时 : 124
//        选择排序1000 次 , 耗时 : 29003
//        平均耗时 : 29
//        插入排序1000 次 , 耗时 : 54698
//        平均耗时 : 54
//        归并排序1000 次 , 耗时 : 1200
//        平均耗时 : 1
//        快速排序1000 次 , 耗时 : 962
//        平均耗时 : 0
//        堆排序1000 次 , 耗时 : 553
//        平均耗时 : 0
//        计数排序1000 次 , 耗时 : 136
//        平均耗时 : 0
//        桶排序1000 次 , 耗时 : 787 ms
//        平均耗时 : 0ms

    }


    public static void sortTest(SortService sortService) {
        long timeConsuming = 0;

        for (int i = 0; i < Note00_Test.testTime; i++) {
            //生成指定长度随机数组
            int[] array = ArrayUtil.generateSpecifyLengthRandomArray(Note00_Test.maxSize, Note00_Test.maxValue);

            int[] copyArray = ArrayUtil.copyArray(array);
//            ArrayUtil.printArray(array);

            long start = System.currentTimeMillis();
            sortService.sort(array);
//            ArrayUtil.printArray(array);
            long end = System.currentTimeMillis();

            //数组自带API排序
            ArrayUtil.sort(copyArray);

            //累积每次排序耗时
//            System.out.println("耗时 : " + (end - start));
            timeConsuming += (end - start);

            //判断排序是否正确
            if (!ArrayUtil.isEqual(array, copyArray)) {
                System.out.println("排序有误");
                break;
            }

        }

        System.out.println(sortService.getName() + Note00_Test.testTime + " 次 , 耗时 : " + timeConsuming + " ms");
        System.out.println("平均耗时 : " + timeConsuming / Note00_Test.testTime + "ms");

    }
}
