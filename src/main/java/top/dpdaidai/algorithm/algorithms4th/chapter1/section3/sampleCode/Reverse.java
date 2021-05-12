package top.dpdaidai.algorithm.algorithms4th.chapter1.section3.sampleCode;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * 使用Stack数据类型 , 将标准输入中的所有整数逆序排列
 *
 * @Author chenpantao
 * @Date 5/12/21 5:06 PM
 * @Version 1.0
 */
public class Reverse {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        while (!StdIn.isEmpty()) {
            stack.push(StdIn.readInt());
        }

        for (Integer integer : stack) {
            StdOut.println(integer);
        }


    }
}
