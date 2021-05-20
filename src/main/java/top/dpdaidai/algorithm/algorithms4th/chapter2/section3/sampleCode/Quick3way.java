package top.dpdaidai.algorithm.algorithms4th.chapter2.section3.sampleCode;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 *
 * 三向切分快速排序
 * Quick3way排序流程 :
 * 原数组
 * F B A F D D D C F B C A B B C A C D A F
 *
 * lo : 0 - F , hi : 19 - F
 * 切分元素 F : lt 16 , gt 19
 *  B A D D D C B C A B B C A C D A F F F F
 *
 * lo : 0 - B , hi : 15 - A
 * 切分元素 B : lt 4 , gt 7
 *  A A A A B B B B C C C D C D D D F F F F
 *
 * lo : 0 - A , hi : 3 - A
 * 切分元素 A : lt 0 , gt 3
 *  A A A A B B B B C C C D C D D D F F F F
 *
 * lo : 8 - C , hi : 15 - D
 * 切分元素 C : lt 8 , gt 11
 *  A A A A B B B B C C C C D D D D F F F F
 *
 * lo : 12 - D , hi : 15 - D
 * 切分元素 D : lt 12 , gt 15
 *  A A A A B B B B C C C C D D D D F F F F
 *
 * isSorted : true
 *
 *  可以看出主键值数量的N倍是运行时间的一个保守上界
 *
 *  对比Quick算法的排序流程 :
 *
 * D C B D F F D B A A B C F D B A C A F C
 *
 * lo : 0 - D , hi : 19 - C
 * 切分元素 : 13 - D
 * D C B C A C A B A A B C B D F D F F F D
 *
 * lo : 0 - D , hi : 12 - B
 * 切分元素 : 12 - D
 * B C B C A C A B A A B C D D F D F F F D
 *
 * lo : 0 - B , hi : 11 - C
 * 切分元素 : 6 - B
 * A B A A A B B C C B C C D D F D F F F D
 *
 * lo : 0 - A , hi : 5 - B
 * 切分元素 : 2 - A
 * A A A A B B B C C B C C D D F D F F F D
 *
 * lo : 0 - A , hi : 1 - A
 * 切分元素 : 1 - A
 * A A A A B B B C C B C C D D F D F F F D
 *
 * lo : 3 - A , hi : 5 - B
 * 切分元素 : 3 - A
 * A A A A B B B C C B C C D D F D F F F D
 *
 * lo : 4 - B , hi : 5 - B
 * 切分元素 : 5 - B
 * A A A A B B B C C B C C D D F D F F F D
 *
 * lo : 7 - C , hi : 11 - C
 * 切分元素 : 10 - C
 * A A A A B B B C C B C C D D F D F F F D
 *
 * lo : 7 - C , hi : 9 - B
 * 切分元素 : 8 - C
 * A A A A B B B B C C C C D D F D F F F D
 *
 * lo : 14 - F , hi : 19 - D
 * 切分元素 : 17 - F
 * A A A A B B B B C C C C D D F D D F F F
 *
 * lo : 14 - F , hi : 16 - D
 * 切分元素 : 16 - F
 * A A A A B B B B C C C C D D D D F F F F
 *
 * lo : 14 - D , hi : 15 - D
 * 切分元素 : 15 - D
 * A A A A B B B B C C C C D D D D F F F F
 *
 * lo : 18 - F , hi : 19 - F
 * 切分元素 : 19 - F
 * A A A A B B B B C C C C D D D D F F F F
 *
 * @Author chenpantao
 * @Date 5/20/21 10:45 AM
 * @Version 1.0
 */
public class Quick3way {

    public static void sort(Comparable[] a) {
        //为保证随机性 , 可打乱数组 , 或者每次随机取切分元素
        StdRandom.shuffle(a);
//        show(a);
//        StdOut.println();
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi < lo) {
            return;
        }

//        StdOut.printf("lo : %s - %s , hi : %s - %s \n", lo, a[lo], hi, a[hi]);

        int lt = lo;
        int gt = hi;
        int i = lo + 1;
        Comparable v = a[lo];

        while (i <= gt) {
            int compare = a[i].compareTo(v);
            if (compare > 0) {
                exch(a, i, gt);
                gt--;
            }
            if (compare < 0) {
                exch(a, i, lt);
                i++;
                lt++;
            }
            if (compare == 0) {
                i++;
            }
        }

//        StdOut.printf("切分元素 %s : lt %s , gt %s \n ", a[lt], lt, gt);
//        show(a);
//        StdOut.println();

        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
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
