package top.dpdaidai.algorithm.algorithms4th.chapter1.section4.sampleCode;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 *
 * 3-sum的解法2 : 先使用归并排序(N * logN) , 再在双层循环中使用二分查找 (N² * logN)
 * g(N) = N² * logN
 *
 * java ThreeSumFast 1Kints.txt
 * array length : 1000
 * Three Sum Fast elapsed time = 0.024
 * 70
 *
 * java ThreeSumFast 2Kints.txt
 * array length : 2000
 * Three Sum Fast elapsed time = 0.089
 * 528
 *
 * java ThreeSumFast 4Kints.txt
 * array length : 4000
 * Three Sum Fast elapsed time = 0.373
 * 4039
 *
 * java ThreeSumFast 8Kints.txt
 * array length : 8000
 * Three Sum Fast elapsed time = 1.55
 * 32074
 *
 * Java ThreeSumFast 1Mints.txt
 *
 *
 * @Author chenpantao
 * @Date 5/15/21 12:12 PM
 * @Version 1.0
 */
public class ThreeSumFast {

    public static int count(int[] a) {
        Arrays.sort(a);
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (BinarySearch.rank(-a[i] - a[j], a) > j) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
        System.out.println("array length : " + a.length);

        Stopwatch timer = new Stopwatch();
        int cnt = count(a);
        StdOut.println("Three Sum Fast elapsed time = " + timer.elapsedTime());
        StdOut.println(cnt);
    }
}
