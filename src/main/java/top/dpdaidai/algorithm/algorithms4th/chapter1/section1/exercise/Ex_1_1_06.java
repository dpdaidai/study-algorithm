package top.dpdaidai.algorithm.algorithms4th.chapter1.section1.exercise;

import edu.princeton.cs.algs4.StdOut;

/**
 * @Author chenpantao
 * @Date 5/10/21 4:08 PM
 * @Version 1.0
 */
public class Ex_1_1_06 {
    public static void main(String[] args) {
        int f = 0;
        int g = 1;
        for (int i = 0; i <= 15; i++)
        {
            StdOut.println(f);
            f = f + g;
            g = f - g;
        }

    }
}
