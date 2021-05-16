package top.dpdaidai.algorithm.algorithms4th.chapter1.section4.sampleCode;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 *
 * 在成本规模翻倍的情况下 , 计算出每次翻倍时算法消耗的时间比值
 * 在大部分情况下 , 比值都会趋向于一个常数(指数级别除外)
 *
 * 1  可用于验证增长数量级是否正确
 * 2  可预测在下一个成本规模时大致消耗时间
 *
 *    250     0.0   2.7
 *    500     0.0   4.0
 *   1000     0.3   8.1
 *   2000     2.0   7.9
 *   4000    16.2   7.9
 *   8000   129.9   8.0
 *
 * @Author chenpantao
 * @Date 5/16/21 12:20 PM
 * @Version 1.0
 */
public class DoublingRatio {

    public static double timeTrial(int N) {
        int MAX = 1000000;
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        Stopwatch s = new Stopwatch();
        int cnt = ThreeSum.count(a);
        return s.elapsedTime();
    }


    public static void main(String[] args) {
        double prev = timeTrial(125);
        for (int N = 250; true; N += N) {
            double time = timeTrial(N);
            StdOut.printf("%6d %7.1f %5.1f\n", N, time, time/prev);
            prev = time;
        }
    }

}
