package top.dpdaidai.algorithm.algorithms4th.chapter2.section1.sampleCode;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 希尔排序
 *
 * @Author chenpantao
 * @Date 5/19/21 3:37 PM
 * @Version 1.0
 */
public class Shell {

    public static void sort(Comparable[] a) {
        int N = a.length;

        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
        int h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            //将数组变为h有序
            for (int i = h; i < N; i++) {
                //将a[i]与 a[i-h], a[i-2h]...进行比较和交换
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }

            h = h / 3;
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
        show(a);
    }

}
