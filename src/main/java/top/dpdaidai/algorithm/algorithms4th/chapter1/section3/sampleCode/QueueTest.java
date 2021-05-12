package top.dpdaidai.algorithm.algorithms4th.chapter1.section3.sampleCode;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * 从文件读取数据放入队列中 , 再将队列中的数据移动到数组中 . 再次过程中数据的顺序不会发生变化
 * main中时In类的readAllInts()的一种实现.
 *
 * @Author chenpantao
 * @Date 5/12/21 4:45 PM
 * @Version 1.0
 */
public class QueueTest {

    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] ints = in.readAllInts();
        Queue<Integer> integers = new Queue<>();
        while (!in.isEmpty()) {
            integers.enqueue(in.readInt());
        }

        int N = integers.size();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = integers.dequeue();
        }

        for (int i = 0; i < N; i++) {
            if (!(ints[i] == a[i])) StdOut.println("error");
        }
        StdOut.println("finished");

    }

}
