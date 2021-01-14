package top.dpdaidai.algorithm.elementary;

/**
 * @Author chenpantao
 * @Date 1/14/21 12:08 PM
 * @Version 1.0
 */
public class Test {

    public static final int testTime = 2000;
    public static final int maxSize = 5000;
    public static final int maxValue = 5000;

    public static void main(String[] args) {
//        Note03_BubbleSort note03_bubbleSort = new Note03_BubbleSort();
//        Test.sortTest(note03_bubbleSort);

//        Note04_SelectionSort note04_selectionSort = new Note04_SelectionSort();
//        Test.sortTest(note04_selectionSort);

        Note05_InsertionSort note05_insertionSort = new Note05_InsertionSort();
        Test.sortTest(note05_insertionSort);

    }


    public static void sortTest(SortService sortService) {
        long timeConsuming = 0;

        for (int i = 0; i < Test.testTime; i++) {
            //生成随机数组
            int[] array = ArrayUtil.generateSpecifyLengthRandomArray(Test.maxSize, Test.maxValue);
            int[] copyArray = ArrayUtil.copyArray(array);
//            ArrayUtil.printArray(array);

            //冒牌排序
            long start = System.currentTimeMillis();
            sortService.sort(array);
//            ArrayUtil.printArray(array);
            long end = System.currentTimeMillis();

            //数组自带API排序
            ArrayUtil.comparator(copyArray);

            //累积每次排序耗时
            System.out.println("耗时 : " + (end - start));
            timeConsuming += (end - start);

            //判断排序是否正确
            if (!ArrayUtil.isEqual(array, copyArray)) {
                System.out.println("排序有误");
                break;
            }

        }

        System.out.println(sortService.getName() + Test.testTime + " 次 , 耗时 : " + timeConsuming);
        System.out.println("平均耗时 : " + timeConsuming / Test.testTime);
    }
}
