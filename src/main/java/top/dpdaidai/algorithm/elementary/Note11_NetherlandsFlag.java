package top.dpdaidai.algorithm.elementary;

/**
 * 荷兰国旗问题
 * 给定一个数组arr，和一个数num，
 * 请把小于num的数放在数组的 左边，
 * 等于num的数放在数组的中间，
 * 大于num的数放在数组的 右边。
 * 要求额外空间复杂度O(1)，时间复杂度O(N)
 *
 * @Author chenpantao
 * @Date 1/18/21 4:32 PM
 * @Version 1.0
 */
public class Note11_NetherlandsFlag {

    public static void main(String[] args) {

        for (int i = 0; i < 5000; i++) {
            int[] array = ArrayUtil.generateSpecifyLengthRandomArray(10, 10);
            int[] yuan = ArrayUtil.copyArray(array);
            int[] copyArray = ArrayUtil.copyArray(array);
//            ArrayUtil.printArray(array);
            int num = (int) ((200 + 1) * Math.random());
//            System.out.println("num: " + num);

//        int[] newArray = normalMethod(array, num);
//        ArrayUtil.printArray(newArray);
            netherlandsFlag(array, num);
//            ArrayUtil.printArray(array);

            partition(copyArray, 0, copyArray.length - 1, num);

            if (!ArrayUtil.isEqual(array, copyArray)) {
                System.out.println("算法有误");
                System.out.println("num: " + num);
                ArrayUtil.printArray(yuan);
                ArrayUtil.printArray(array);
                ArrayUtil.printArray(copyArray);
                break;
            }

        }


    }

    //常规算法
    //生成一个额外的数组 , 将小数挨个放在左边 , 大数挨个放在右边, 中间填上num
    //时间复杂度O(N) , 空间复杂度O(N)
    public static int[] normalMethod(int[] array, int num) {
        int[] newArray = new int[array.length];

        int left = 0;
        int right = array.length - 1;

        for (int i = 0; i < array.length; i++) {
            if (array[i] < num) {
                newArray[left++] = array[i];
            }
            if (array[i] > num) {
                newArray[right--] = array[i];
            }
        }

        while (left < right + 1) {
            newArray[left++] = num;
        }
        return newArray;
    }

    //要求空间复杂度O(1) , 那么不能借助额外的数据数组
    public static void netherlandsFlag(int[] array, int num) {
        //小于num的部分左边界
        int left = -1;

        //大于num的部分右边界
        int right = array.length;

        //当前指针位置
        int current = 0;

        while (current < right) {
            if (array[current] == num) {
                current++;
            } else if (array[current] < num) {
                ++left;
                int swap = array[current];
                array[current] = array[left];
                array[left] = swap;
                current++;
            } else if (array[current] > num) {
                --right;
                int swap = array[current];
                array[current] = array[right];
                array[right] = swap;
            }

            //            ArrayUtil.printArray(array);

        }
//        System.out.println(current);
//        System.out.println(left);
//        System.out.println(right);

    }

    //左神答案
    public static int[] partition(int[] arr, int l, int r, int p) {
        int less = l - 1;
        int more = r + 1;
        while (l < more) {
            if (arr[l] < p) {
                swap(arr, ++less, l++);
            } else if (arr[l] > p) {
                swap(arr, --more, l);
            } else {
                l++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    // for test
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


}
