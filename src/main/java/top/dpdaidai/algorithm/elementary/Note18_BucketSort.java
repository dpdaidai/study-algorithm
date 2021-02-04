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
public class Note18_BucketSort implements SortService {

    public static final String NAME = "桶排序";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void sort(int[] array) {
        bucketSort(array, 200);
    }


    /**
     *
     * @param array
     * @param bucketSize 每个桶的数据最大差值
     */
    public static void bucketSort(int[] array, int bucketSize) {
        if (array == null || array.length < 2) return;

        //1  找出原数组A中最大和最小数
        int min = array[0];
        int max = array[0];

        for (int i = 0; i < array.length; i++) {
            min = array[i] < min ? array[i] : min;
            max = array[i] > max ? array[i] : max;
        }

        //2 求出每个桶的区间, +1 是因为一般不会恰好整数个桶
        int bucketCount = (max - min)/bucketSize + 1;

        //3  新建桶
        int[][] buckets = new int[bucketCount][0];

        //4  将array中的数据放入各个桶中
        for (int i = 0; i < array.length; i++) {
            //判断array[i]属于哪个桶
            int index = (array[i] - min) / bucketSize;
            buckets[index] = ArrayUtil.arrayAppend(buckets[index], array[i]);
        }

        //5  使用快排对每个桶进行排序, 使桶内部有序
        for (int i = 0; i < buckets.length; i++) {
            Note17_CountingSort note17_countingSort = new Note17_CountingSort();
            note17_countingSort.sort(buckets[i]);
        }

        //6  将桶内数据按顺序放入原数组中
        int index = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i].length == 0) continue;

            for (int j = 0; j < buckets[i].length; j++) {
                array[index] = buckets[i][j];
                index++;
            }

        }

    }

}
