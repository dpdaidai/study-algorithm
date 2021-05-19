package top.dpdaidai.algorithm.algorithms4th.chapter2.section2.sampleCode;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * 自顶向下的归并排序
 *
 * @Author chenpantao
 * @Date 5/19/21 6:18 PM
 * @Version 1.0
 */
public class Merge {

    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;

        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {

        //将元素复制到辅助数组
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        //左侧子数组的游标
        int i = lo;
        //右侧子数组游标
        int j = mid + 1;
        //归并两个子数组
        for (int k = lo; k <= hi; k++) {
            //左侧子数组取尽
            if (i > mid) a[k] = aux[j++];
                //右侧子数组取尽
            else if (j > hi) a[k] = aux[i++];
                //比较左右子数组当前游标的值
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
//        StdOut.println("lo : " + lo + ", hi  : " + hi);
//        show(a);
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
//        show(a);
        sort(a);
        StdOut.println("isSorted : " + isSorted(a));
//        show(a);
    }

}
