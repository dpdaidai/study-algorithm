package top.dpdaidai.algorithm.algorithms4th.chapter2.section4.sampleCode;

import edu.princeton.cs.algs4.*;

/**
 *
 * 打印输入流中最小的M行
 *
 * > cat tinyBatch.txt
 * Turing      6/17/1990   644.08
 * vonNeumann  3/26/2002  4121.85
 * Dijkstra    8/22/2007  2678.40
 * vonNeumann  1/11/1999  4409.74
 * Dijkstra   11/18/1995   837.42
 * Hoare       5/10/1993  3229.27
 * vonNeumann  2/12/1994  4732.35
 * Hoare       8/18/1992  4381.21
 * Turing      1/11/2002    66.10
 * Thompson    2/27/2000  4747.08
 * Turing      2/11/1991  2156.86
 * Hoare       8/12/2003  1025.70
 * vonNeumann 10/13/1993  2520.97
 * Dijkstra    9/10/2000   708.95
 * Turing     10/12/1993  3532.36
 * Hoare       2/10/2005  4050.20
 *
 * > java TopM 3 < tinyBatch.txt
 * Turing      1/11/2002    66.10
 * Turing      6/17/1990   644.08
 * Dijkstra    9/10/2000   708.95
 *
 * @Author chenpantao
 * @Date 5/20/21 4:04 PM
 * @Version 1.0
 */
public class TopM {

    public static void main(String[] args) {
        int M = Integer.parseInt(args[0]);
        MaxPQ<Transaction> pq = new MaxPQ<>(M + 1);
        while (StdIn.hasNextLine()) {
            //为下一行输入创建一个元素并放入优先队列中
            pq.insert(new Transaction(StdIn.readLine()));

            //如果优先队列中存在M+1个元素 , 则删除其中最小的元素
            if (pq.size() > M) {
                pq.delMax();
            }
        }

        Stack<Transaction> stack = new Stack<>();
        while (!pq.isEmpty()) {
            stack.push(pq.delMax());
        }

        for (Transaction transaction : stack) {
            StdOut.println(transaction);
        }

    }

}
