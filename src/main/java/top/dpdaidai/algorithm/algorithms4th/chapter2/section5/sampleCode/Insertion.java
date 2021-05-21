package top.dpdaidai.algorithm.algorithms4th.chapter2.section5.sampleCode;

import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

/**
 *
 * https://algs4.cs.princeton.edu/25applications/Insertion.java.html
 *
 * 新增 void sort(Object[] a, Comparator comparator)
 * 可以适用于任何 Comparator
 *
 * @Author chenpantao
 * @Date 5/21/21 7:53 PM
 * @Version 1.0
 */
public class Insertion {
    private Insertion() {
    }

    public static void sort(Object[] a, Comparator comparator) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1], comparator); j--) {
                exch(a, j, j - 1);
            }
            assert isSorted(a, 0, i, comparator);
        }
        assert isSorted(a, comparator);
    }

    public static void sort(Object[] a, int lo, int hi, Comparator comparator) {
        for (int i = lo + 1; i < hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1], comparator); j--) {
                exch(a, j, j - 1);
            }
        }
        assert isSorted(a, lo, hi, comparator);
    }


    // is v < w ?
    private static boolean less(Object v, Object w, Comparator comparator) {
        return comparator.compare(v, w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


    private static boolean isSorted(Object[] a, Comparator comparator) {
        return isSorted(a, 0, a.length, comparator);
    }

    // is the array a[lo..hi) sorted
    private static boolean isSorted(Object[] a, int lo, int hi, Comparator comparator) {
        for (int i = lo + 1; i < hi; i++)
            if (less(a[i], a[i - 1], comparator)) return false;
        return true;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }


}
