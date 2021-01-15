package top.dpdaidai.algorithm.elementary;

/**
 *
 * 对数器的使用 : 大样本测试准备
 *
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
public class Note06_Test {

    public static final int testTime = 2000;
    public static final int maxSize = 5000;
    public static final int maxValue = 5000;

    public static void main(String[] args) {
        Note03_BubbleSort note03_bubbleSort = new Note03_BubbleSort();
        Note06_Test.sortTest(note03_bubbleSort);

        Note04_SelectionSort note04_selectionSort = new Note04_SelectionSort();
        Note06_Test.sortTest(note04_selectionSort);

        Note05_InsertionSort note05_insertionSort = new Note05_InsertionSort();
        Note06_Test.sortTest(note05_insertionSort);

//        冒泡排序2000 次 , 耗时 : 55765
//        平均耗时 : 27
//        选择排序2000 次 , 耗时 : 15131
//        平均耗时 : 7
//        插入排序2000 次 , 耗时 : 27733
//        平均耗时 : 13
    }


    public static void sortTest(SortService sortService) {
        long timeConsuming = 0;

        for (int i = 0; i < Note06_Test.testTime; i++) {
            //生成指定长度随机数组
            int[] array = ArrayUtil.generateSpecifyLengthRandomArray(Note06_Test.maxSize, Note06_Test.maxValue);

            int[] copyArray = ArrayUtil.copyArray(array);
//            ArrayUtil.printArray(array);

            long start = System.currentTimeMillis();
            sortService.sort(array);
//            ArrayUtil.printArray(array);
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

        System.out.println(sortService.getName() + Note06_Test.testTime + " 次 , 耗时 : " + timeConsuming);
        System.out.println("平均耗时 : " + timeConsuming / Note06_Test.testTime);
    }
}
