package top.dpdaidai.algorithm.algorithms4th.chapter2.section4.sampleCode;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 堆排序
 *
 * @Author chenpantao
 * @Date 5/21/21 4:12 PM
 * @Version 1.0
 */
public class Heap {
    public static void sort(Comparable[] a) {

        int N = a.length;
        //构造堆
        for (int i = (N - 1) / 2; i >= 0; i--) {
            sink(a, i, N);
        }

        //下沉排序
        while (N > 1) {
            //将最大值交换至数组最后
            exch(a, 0, N - 1);
            //堆大小减一
            N--;
            //恢复堆大小
            sink(a, 0, N);
        }

    }

    public static void sink(Comparable[] a, int i, int N) {
        //判断是都有子节点
        while (2 * i + 1 <= N - 1) {
            int j = 2 * i + 1;
            //判断两个子节点的大小
            if (j + 1 <= N - 1 && less(a[j], a[j + 1])) {
                j = j + 1;
            }
            //判断父节点和子节点的大小
            if (less(a[j], a[i])) break;
            //如果父节点小于子节点 , 交换位置
            exch(a, i, j);
            i = j;
        }
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
