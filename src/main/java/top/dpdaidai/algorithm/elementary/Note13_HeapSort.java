package top.dpdaidai.algorithm.elementary;

/**
 *
 * 堆排序
 * 重点是堆的结构 , 完全二叉树
 * 完全二叉树 : 从左往右补齐节点
 * 满二叉树 : 这种树的特点是每一层上的节点数都是最大节点数。
 *
 * 堆 : 堆就是完全二叉树
 *  大根堆 : 任何一个子数的最大值就是它头部
 *  小根堆 : 任何一个子数的最小值就是它头部
 *
 * 1  建立大根堆 : 时间复杂度和二叉树的高度相关
 *      每次加入一个新节点, 调整该节点位置的时间复杂度为 log(N-1)
 *      O(N) = log1 + log2 + log3 + ... + log(N-1)
 * 2  堆内某个数据变化了 , 需要重新排序
 *      和元数据比较是变大了还是变小了
 *      然后再选择和子节点还是父节点边角
 *      寻找index的子节点 : 左侧 index*2 +1 , 右侧 index*2 + 2
 *      寻找index的父节点 : (index-1)/2
 * 3  将堆内某个数据推出 :
 *      将堆最后一个节点和该节点交换
 *      堆的size-1
 *      最后将被交换的节点重新排序
 * 4  假如 某个数组不停的往外吐数据, 需要随时快速的得知吐出所有数据的中位数
 *      分别建立一个大根堆和一个小根堆
 *      大根堆保存数据中较小的一半数
 *      小根堆中保存所有数据中较大的一半数
 *      每次新加数据时, 比较比大根堆中最大的数大还是小. 大的话放入小根堆, 小的话放入大根堆中
 *      当两个堆的数据量差值大于2时, 将数据较多的堆中的最上面一个数移动到另外一个堆中
 *      这样, 大根堆和小根堆中最上面的数就是中位数
 *
 * 堆排序 :
 *  1   形成大根堆  , O(n)
 *  2   每次将堆最后一个数和堆定的数据交换
 *  3   堆的size-1
 *  4   对堆再次进行排序, 这样再次得到大根堆
 *  5   重复2-3-4 , 直到堆的数据都被推出来
 *
 * 时间复杂度 :
 *    形成大根堆 O(n) + 每次从堆中去一个最大值, 并重新为堆顶元素排序的 O(N*logN)
 *    时间复杂度 O(N*logN) , 空间复杂度 O(1)
 *
 * @Author chenpantao
 * @Date 1/22/21 12:50 PM
 * @Version 1.0
 */
public class Note13_HeapSort implements SortService {

    public static final String NAME = "堆排序";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void sort(int[] array) {
        heapSort(array);
    }

    //堆排序
    public static void heapSort(int[] array) {
        if (array == null || array.length < 2) return;

        //1  将数组整理为大根堆结构
        for (int i = 0; i < array.length; i++) {
            heapInsert(array, i);
        }

        //2  将堆顶数据和数组尾部数据交换
        //3  堆size-1
        //4  因为交换过来的堆顶的数, 需要重新整理堆的结构为大根堆
        //5  重复2-3-4 , 直到堆内数据为0或者1
        int heapSize = array.length;
        while (heapSize > 1) {
            ArrayUtil.swap(array, 0, heapSize - 1);
            heapSize--;
            reorder(array, 0, heapSize);
        }

    }

    //为堆增加节点
    public static void heapInsert(int[] array, int index) {
        //1  如果index的父节点比index的值小,
        //2  那么交换,
        //3  直到新增的节点到达堆顶
        int father = (index - 1) / 2;
        while (array[father] < array[index]) {
            ArrayUtil.swap(array, father, index);
            index = father;
            father = (index - 1) / 2;
        }
    }

    //因为堆顶替换了数, 将堆重新为排为大根堆
    public static void reorder(int[] array, int index, int heapSize) {
        //1 从子节点中选举一个较大的数
        //2 如果父节点比子节点小, 那么父子交换
        //3 直到父节点比子节点大, 或者超过堆的边界
        int latestIndex = heapSize - 1;
        while (index < latestIndex) {
            int childLeft = index * 2 + 1;
            int childRight = index * 2 + 2;
            if (childLeft > latestIndex && childRight > latestIndex) {
                break;
            }

            int largerChild;

            if (childRight > latestIndex) {
                largerChild = childLeft;
            } else {
                largerChild = array[childLeft] > array[childRight] ? childLeft : childRight;
            }

            if (array[index] < array[largerChild]) {
                ArrayUtil.swap(array, index, largerChild);
                index = largerChild;
            } else {
                break;
            }

        }

    }

}
