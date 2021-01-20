package top.dpdaidai.algorithm.elementary;

/**
 * 快速排序
 * 利用荷兰国旗问题来改进快速排序
 * 每次选取数据区间最右边的数 , 来将数据划分为 小于, 等于 , 大于三个区间 .
 * 再对小于, 大于连个区间同样递归处理
 * <p>
 * 时间复杂度类似归并排序 = O(N*logN)
 * 额外空间复杂度O(logN) :
 *  在接近二分的过程中, 每次需要记录被选取的num数的index, 一共需要O(logN)个点来记录
 * <p>
 * 经典快排的问题 :
 * 取数据区间最右边的值, 很可能画出来的区间 , 左右两边的规模不相等
 * 当数据本身为有序时, 每次只能排一次数, 时间复杂度变为O(n²)
 * <p>
 * 改进办法 : 随机快排
 * 每次不是取最右边的数来进行划分 , 二是从数组中随机取一个数, 来进行划分.
 * 这样复杂度虽然还是有可能出现最差情况, 但这是概率时间 , 和数组本身顺序无关了
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

        //丛数组中选取随机数放到最右边
        swap(array, left + (int) (Math.random() * (right - left + 1)), right);

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
