package top.dpdaidai.algorithm.algorithms4th.chapter1.section1.exercise;

import edu.princeton.cs.algs4.StdOut;

/**
 * @Author chenpantao
 * @Date 5/10/21 4:09 PM
 * @Version 1.0
 */
public class Ex_1_1_07 {

    public static void main(String[] args) {
        double t = 9.0;
        while (Math.abs(t - 9.0 / t) > .00001) {
            t = (9.0 / t + t) / 2.0;
            StdOut.printf("%.5f\n", t);
        }

    }
}
