package top.dpdaidai.algorithm.elementary;

/**
 *
 * 桶排序 , 非基于比较的排序 , 与被排序的样本的实际数据状况很有关系，所以实际中并不经常使用
 * 时间复杂度 O(N), 额外空间复杂度O(N)
 *
 * 桶排序的优点 :
 *   +  可以将桶分类作为外部类算法
 *          如果需要对列表进行排序, 以至于无法将其放入内存中, 则可以通过RAM流式传输列表, 将项目分发到存储在外部文件中的存储桶中,
 *          然后分别对RAM中的每个文件排序.
 *   +  一旦将元素分配到桶中, 就可以独立处理每个桶
 *   +  可以并行为每个桶排序
 *   +  当数据或多或少地均匀分布时，或者在基于输入数组进行快速启发式设置的情况下，有一种智能的方式来选择存储桶时，存储桶排序最有效。
 *
 * 缺点 :
 *   +  由于需要良好的存储方案, 因此无法将其用于所有数据类型
 *   +  存储桶排序的效率对输入值的分布很敏感, 因此, 如果数据紧密聚集的排列, 则不值得
 *   +  性能取决于所选存储桶的数量, 与其它算法相比, 这可能需要一些额外的性能调整
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
