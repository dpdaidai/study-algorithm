package top.dpdaidai.algorithm.elementary;

/**
 * 快速排序
 * 利用荷兰国旗问题来改进快速排序
 *
 * @Author chenpantao
 * @Date 1/20/21 9:52 AM
 * @Version 1.0
 */
public class Note12_QuickSort implements SortService {

    public static final String NAME = "快速排序";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    // 1  选取数组最右边的一个数num
    // 2  使数组分为小于num, 等于num, 大于num三个区间
    // 3  使用分治的思想, 再对左右两个区间的数据进行同样的处理
    // 4  直到区间内的数据量小于2
    public static void quickSort(int[] array, int left, int right) {

        //脏数据判断
        if (array == null || array.length < 2) return;

        //快排终止条件 : 当分区的数据仅有一个数或者没有数时
        if (left >= right) return;

        int[] indexInt = dataGroup(array, left, right);
        quickSort(array, left, indexInt[0]);
        quickSort(array, indexInt[1], right);
    }

    //将数据根据num分为 小于, 等于, 大于三组
    //返回值为 等于num的数的左边和右边 两个坐标
    public static int[] dataGroup(int[] array, int left, int right) {

        //选取数组最右边的数为num
        int num = array[right];

        //小于num的数据区间右坐标
        int less = left - 1;

        //大于num的数据区间左坐标
        int more = right + 1;

        //当前遍历指针
        int current = left;

        while (current < more) {
            if (array[current] < num) {
                //当前数小于num 时, 将小区间的坐标右移
                less++;
                swap(array, current, less);
                current++;
            } else if (array[current] > num) {
                //当前数大于num时, 将大区间的坐标左移
                more--;
                swap(array, current, more);
            } else if (array[current] == num) {
                current++;
            }
        }

        //返回num左右两侧的坐标
        return new int[]{less, more};
    }

    public static void swap(int[] array, int i, int j) {
        int swap = array[i];
        array[i] = array[j];
        array[j] = swap;
    }

}
