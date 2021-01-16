package top.dpdaidai.algorithm.elementary;

/**
 * 冒泡排序
 * 每一轮遍历 , 找当前最大的数 , 并将它逐渐移动到最后一个位置
 * 时间复杂度 : O(n²) ,额外空间复杂度O(1)
 * 测试数据 :
 * 数组长度 10000
 * 数大小 -5000 - +5000
 * 运行次数 1000
 * <p>
 * 结论数据 :
 * 运行机器 : MBP2015 15寸
 * 排序总耗时 : 124848 ms
 * 平均排序耗时 : 124 ms
 *
 * @Author chenpantao
 * @Date 1/13/21 11:56 AM
 * @Version 1.0
 */
public class Note03_BubbleSort implements SortService {

    public static final String NAME = "冒泡排序";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void sort(int[] array) {
        bubbleSort(array);
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
