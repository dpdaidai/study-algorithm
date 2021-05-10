package top.dpdaidai.algorithm.algorithms4th.chapter1.section1.exercise;

import edu.princeton.cs.algs4.StdOut;

/**
 * @Author chenpantao
 * @Date 5/10/21 4:11 PM
 * @Version 1.0
 */
public class Ex_1_1_09 {

    // 将一个正整数N用二进制表示并转换为一个String类型的值
    public static void main(String[] args) {
        int N = 234;
        StdOut.println(Integer.toBinaryString(N));

        String s = "";
        for (int n = N; n > 0; n /= 2) {
            s = n % 2 + s;

        }
        StdOut.println(s);

    }
}
