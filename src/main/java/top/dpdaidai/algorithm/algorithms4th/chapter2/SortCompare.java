package top.dpdaidai.algorithm.algorithms4th.chapter2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import top.dpdaidai.algorithm.algorithms4th.chapter2.section1.sampleCode.Insertion;
import top.dpdaidai.algorithm.algorithms4th.chapter2.section1.sampleCode.Selection;
import top.dpdaidai.algorithm.algorithms4th.chapter2.section1.sampleCode.Shell;

/**
 *
 * 比较两种排序算法
 *
 * @Author chenpantao
 * @Date 5/19/21 11:40 AM
 * @Version 1.0
 */
public class SortCompare {

    public static double time(String alg, Comparable[] a) {
        Stopwatch stopwatch = new Stopwatch();
        if (alg.equals("Insertion")) Insertion.sort(a);
//        else if (alg.equals("InsertionX"))      InsertionX.sort(a);
//        else if (alg.equals("BinaryInsertion")) BinaryInsertion.sort(a);
        else if (alg.equals("Selection")) Selection.sort(a);
//        else if (alg.equals("Bubble"))          Bubble.sort(a);
        else if (alg.equals("Shell")) Shell.sort(a);
//        else if (alg.equals("Merge"))           Merge.sort(a);
//        else if (alg.equals("MergeX"))          MergeX.sort(a);
//        else if (alg.equals("MergeBU"))         MergeBU.sort(a);
//        else if (alg.equals("Quick"))           Quick.sort(a);
//        else if (alg.equals("Quick3way"))       Quick3way.sort(a);
//        else if (alg.equals("QuickX"))          QuickX.sort(a);
//        else if (alg.equals("Heap"))            Heap.sort(a);
//        else if (alg.equals("System"))          Arrays.sort(a);
        else throw new IllegalArgumentException("Invalid algorithm: " + alg);
        return stopwatch.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T) {
        //使用算法alg, 将T个长度为N的数组排序 , 并返回耗时
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++) {
                a[i] = StdRandom.uniform();
            }
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        StdOut.printf("%s time is %f \n", alg1, t1);
        StdOut.printf("%s time is %f \n", alg2, t2);
        StdOut.printf("%s is %.1f times faster than %s", alg1, t2 / t1, alg2);

    }


}
