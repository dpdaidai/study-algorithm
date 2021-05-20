package top.dpdaidai.algorithm.algorithms4th.chapter2.section3.sampleCode;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 *
 * 快速排序
 *
 * 排序流程如下 :
 *
 * 原始数组 :
 * O S E X E M A T P L R
 *
 * lo : 0 - O , hi : 10 - R
 * 切分元素 : 5 - O
 * M L E A E O X T P S R
 *
 * lo : 0 - M , hi : 4 - E
 * 切分元素 : 4 - M
 * E L E A M O X T P S R
 *
 * lo : 0 - E , hi : 3 - A
 * 切分元素 : 2 - E
 * E A E L M O X T P S R
 *
 * lo : 0 - E , hi : 1 - A
 * 切分元素 : 1 - E
 * A E E L M O X T P S R
 *
 * lo : 6 - X , hi : 10 - R
 * 切分元素 : 10 - X
 * A E E L M O R T P S X
 *
 * lo : 6 - R , hi : 9 - S
 * 切分元素 : 7 - R
 * A E E L M O P R T S X
 *
 * lo : 8 - T , hi : 9 - S
 * 切分元素 : 9 - T
 * A E E L M O P R S T X
 *
 * isSorted : true
 *
 * @Author chenpantao
 * @Date 5/20/21 10:45 AM
 * @Version 1.0
 */
public class Quick {

    public static void sort(Comparable[] a) {
        //为保证随机性 , 可打乱数组 , 或者每次随机取切分元素
        StdRandom.shuffle(a);
//        show(a);
//        StdOut.println();
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
//        StdOut.printf("lo : %s - %s , hi : %s - %s \n", lo, a[lo], hi, a[hi]);
        //寻找切分元素的坐标
        int partition = partition(a, lo, hi);
//        StdOut.println("切分元素 : " + partition + " - " + a[partition]);
//        show(a);
//        StdOut.println();

        //将切分元素左侧排序
        sort(a, lo, partition - 1);
        //将切分元素右侧排序
        sort(a, partition + 1, hi);
    }

    public static int partition(Comparable[] a, int lo, int hi) {

        //取最左侧元素为切分元素

        //左侧子数组游标
        int left = lo;
        //右侧子数组游标
        int right = hi + 1;

        //寻找左侧大于切分元素的元素
        //寻找右侧小于切分元素的元素
        //将两个元素交换 , 然后进行下一轮
        while (true) {

            while (less(a[++left], a[lo])) if (left == hi) break;
            while (less(a[lo], a[--right])) ;

            if (left >= right) {
                break;
            }

            exch(a, left, right);

        }

        //将lo和right交换 , right就是切分元素的坐标
        exch(a, lo, right);
        return right;
    }

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // is the array sorted from a[lo] to a[hi]
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    // Read strings from standard input, sort them, and print.
    public static void main(String[] args) {
        String[] a = In.readStrings();
        sort(a);
        StdOut.println("isSorted : " + isSorted(a));
    }

}
