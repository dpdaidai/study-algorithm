package top.dpdaidai.algorithm.elementary;

/**
 *
 * 桶排序 , 非基于比较的排序 , 与被排序的样本的实际数据状况很有关系，所以实际中并不经常使用
 * 时间复杂度 O(N), 额外空间复杂度O(N)
 *      +  计数排序
 *      +  基数排序
 *
 * @Author chenpantao
 * @Date 1/24/21 9:58 PM
 * @Version 1.0
 */
public class Note18_BucketSort implements SortService{

    public static final String NAME = "桶排序";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void sort(int[] array) {
        bucketSort(array);
    }

    public static void bucketSort(int[] array){


    }

}
