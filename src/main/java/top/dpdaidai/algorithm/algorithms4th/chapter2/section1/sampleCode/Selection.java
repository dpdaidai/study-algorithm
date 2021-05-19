package top.dpdaidai.algorithm.algorithms4th.chapter2.section1.sampleCode;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 选择排序
 *
 * @Author chenpantao
 * @Date 5/19/21 10:22 AM
 * @Version 1.0
 */
public class Selection {

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[min])) min = j;
            }
//            show(a);
            exch(a, i, min);
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
//        assert isSorted(a);
        StdOut.println("isSorted : " + isSorted(a));
        show(a);
    }

}
