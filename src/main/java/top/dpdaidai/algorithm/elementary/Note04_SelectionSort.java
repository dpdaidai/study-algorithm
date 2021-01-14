package top.dpdaidai.algorithm.elementary;

/**
 * 选择排序
 * 冒泡排序的升级版 , 冒泡排序每一轮数组内都涉及多次数据的交换 .
 * 选择排序则是每一轮选出一个最小的数 , 每一轮只交换一次数据
 *
 * 时间复杂度 : O(n²) ,额外空间复杂度O(1)
 * 测试数据 :
 * 数组长度 5000
 * 数大小 0-5000
 * 运行次数 2000
 * <p>
 * 结论数据 :
 * 运行机器 : MBP2015 15寸
 * 排序总耗时 : 14758 ms
 * 平均排序耗时 : 7 ms
 *
 * @Author chenpantao
 * @Date 1/14/21 12:07 PM
 * @Version 1.0
 */
public class Note04_SelectionSort implements SortService {

    public static final String NAME = "选择排序";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void sort(int[] array) {
        SelectionSort(array);
    }

    public static void SelectionSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int swap = array[minIndex];
            array[minIndex] = array[i];
            array[i] = swap;
        }

    }
}
