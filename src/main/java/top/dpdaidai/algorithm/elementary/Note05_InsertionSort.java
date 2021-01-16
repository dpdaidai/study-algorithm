package top.dpdaidai.algorithm.elementary;

/**
 * 插入排序
 * 相比于冒泡排序和选择排序 , 插入排序的消耗时间和数组本身的顺序有很大的关系 .
 * 评价一个算法 , 一律按照数据情况最差时计算时间复杂度 .
 * 最好情况 : 当数组本身是正序时 , 时间复杂度能达到 O(n)
 * 最差情况 : 当数组本身是逆序时 , 时间复杂度完全等于冒泡排序 O(n²)
 * 平均情况 :
 * 时间复杂度 : O(n²) , 额外空间复杂度 O(1)
 * 测试数据 :
 * 数组长度 10000
 * 数大小 -5000 - +5000
 * 运行次数 1000
 * <p>
 * 结论数据 :
 * 运行机器 : MBP2015 15寸
 * 排序总耗时 : 54698 ms
 * 平均排序耗时 : 54 ms
 *
 * @Author chenpantao
 * @Date 1/14/21 11:36 AM
 * @Version 1.0
 */
public class Note05_InsertionSort implements SortService {

    public static final String NAME = "插入排序";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void sort(int[] array) {
        insertionSort(array);
    }

    public static void insertionSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        for (int i = 1; i < array.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (array[j] > array[j + 1]) {
                    int swap = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = swap;

                }
            }
        }
    }
}
