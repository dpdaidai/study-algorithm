package top.dpdaidai.algorithm.elementary;

import java.util.Arrays;

/**
 *
 * 基数的时间复杂度为O(N)，不过他是忽略了常数项，即实际排序时间为kn(其中k是常数项)，
 * 然而在实际排序的过程中，这个常数项k其实是很大的，这会很大程度影响实际的排序时间，
 * 而像快速排序虽然是O(N*logN)，但它前面的常数项是相对比较小的，影响也相对比较小。
 *
 * 需要说明的是，基数排序也并非比快速排序慢，这得看具体情况，。而且，数据量越大的话，基数排序会越有优势。
 *
 * 本例算法效率 :
 *  1  常数 k 经常大于 logN
 *  2  radixSort1() 解法中 , 使用桶排序 , 各个桶每次添加数据时都需要自动给数组扩容, 扩容次数为 k*N 次, 开销很大
 *  3  radixSort() 解法利用计数排序辅助
 *
 * 参考资料 :
 *  https://www.runoob.com/w3cnote/radix-sort.html
 *  https://www.pdai.tech/md/algorithm/alg-sort-x-radix.html
 *  https://www.youtube.com/watch?v=YXFI4osELGU&list=PLG6ePePp5vvYVEjRanyndt7ZSqTzillom&index=16
 *  https://www.wukong.com/question/6514178138723844366/
 *  https://code.i-harness.com/zh-CN/q/e98fa9
 *
 * @Author chenpantao
 * @Date 2/5/21 7:44 PM
 * @Version 1.0
 */
public class Note19_RadixSort implements SortService {

    public static final String NAME = "基数排序";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void sort(int[] array) {
        //解法1
        radixSort(array);

        //解法2 , 效率太低
//        int maxDigit = getMaxDigit(array);
//        radixSort(array, maxDigit);
    }

    /*
     * 获取数组a中最大值
     *
     * 参数说明:
     *     a -- 数组
     *     n -- 数组长度
     */
    private static int getMax(int[] a) {
        int max;

        max = a[0];
        for (int i = 1; i < a.length; i++)
            if (a[i] > max)
                max = a[i];

        return max;
    }

    /*
     * 对数组按照"某个位数"进行排序(桶排序)
     *
     * 参数说明:
     *     a -- 数组
     *     exp -- 指数。对数组a按照该指数进行排序。
     *
     * 例如，对于数组a={50, 3, 542, 745, 2014, 154, 63, 616}；
     *    (01) 当exp=1表示按照"个位"对数组a进行排序
     *    (02) 当exp=10表示按照"十位"对数组a进行排序
     *    (03) 当exp=100表示按照"百位"对数组a进行排序
     *    ...
     *
     */
    private static void countSort(int[] a, int exp) {
        int[] output = new int[a.length];    // 存储"被排序数据"的临时数组

        //bucket 为桶的个数 , 因为有负数 , 所以桶为20个, 前十个桶存储负数
        int bucket = 10;
        int[] buckets = new int[bucket * 2];

        // 将数据出现的次数存储在buckets[]中
        for (int i = 0; i < a.length; i++)
            buckets[(a[i] / exp) % 10 + bucket]++;

        // 更改buckets[i]。目的是让更改后的buckets[i]的值，是该数据在output[]中的位置。
        for (int i = 1; i < 10 * 2; i++)
            buckets[i] += buckets[i - 1];

        // 将数据存储到临时数组output[]中
        for (int i = a.length - 1; i >= 0; i--) {
            int bucketIndex = (a[i] / exp) % 10 + bucket;
            output[buckets[bucketIndex] - 1] = a[i];
            buckets[bucketIndex]--;
        }

        // 将排序好的数据赋值给a[]
        for (int i = 0; i < a.length; i++)
            a[i] = output[i];

    }

    /*
     * 基数排序
     *
     * 参数说明:
     *     a -- 数组
     */
    public static void radixSort(int[] a) {
        int exp;    // 指数。当对数组按各位进行排序时，exp=1；按十位进行排序时，exp=10；...
        int max = getMax(a);    // 数组a中的最大值

        // 从个位开始，对数组a按"指数"进行排序
        for (exp = 1; max / exp > 0; exp *= 10)
            countSort(a, exp);
    }


    /**
     * 获取最高位数
     */
    private int getMaxDigit(int[] arr) {
        int maxValue = getMaxValue(arr);
        return getNumberLength(maxValue);
    }

    private int getMaxValue(int[] arr) {
        int maxValue = arr[0];
        for (int value : arr) {
            if (maxValue < value) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    protected int getNumberLength(long num) {
        if (num == 0) {
            return 1;
        }
        int lenght = 0;
        for (long temp = num; temp != 0; temp /= 10) {
            lenght++;
        }
        return lenght;
    }

    private int[] radixSort1(int[] arr, int maxDigit) {
        int mod = 10;  //取模
        int dev = 1;   //当前位数
        int buckets = 10;  //桶的数量

        for (int i = 0; i < maxDigit; i++, dev *= 10, mod *= 10) {
            // 考虑负数的情况，这里扩展一倍队列数，其中 [0-9]对应负数，[10-19]对应正数 (bucket + 10)
            int[][] counter = new int[buckets * 2][0];

            for (int j = 0; j < arr.length; j++) {
                //根据当前数该进哪一个桶
                int bucket = ((arr[j] % mod) / dev) + buckets;
                counter[bucket] = arrayAppend(counter[bucket], arr[j]);
            }

            int index = 0;
            for (int[] bucket : counter) {
                for (int value : bucket) {
                    arr[index++] = value;
                }
            }
        }

        return arr;
    }

    /**
     * 自动扩容，并保存数据
     *
     * @param arr
     * @param value
     */
    private int[] arrayAppend(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }
}
