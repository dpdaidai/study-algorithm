package top.dpdaidai.algorithm.algorithms4th.chapter1.section4.sampleCode;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 *
 * 1. 程序在不同计算机上的运行时间只比通常时一个常数
 * 2. 将问题规模和程序运行时间作为函数,  绘制成图表 , 进而得到关于运行时间的函数表达式
 * 3. 由2的函数表达式 , 预测更大数据时的运行时间 , 进而验证表达式的正确性
 *
 * % java DoublingTest
 *     250   0.0
 *     500   0.0
 *    1000   0.1
 *    2000   2.0
 *    4000   3.3
 *    8000  26.3
 *   16000 215.1
 *
 *   T(N) = aN^3
 *
 * @Author chenpantao
 * @Date 5/13/21 7:43 PM
 * @Version 1.0
 */
public class DoublingTest {
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
        for (int N = 250; true; N += N) {
            double time = timeTrial(N);
            StdOut.printf("%7d %5.1f\n", N, time);
        }
    }
}
