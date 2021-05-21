package top.dpdaidai.algorithm.algorithms4th.chapter2.section4.sampleCode;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * 索引优先队列用例
 * 将命令行多个输入归并为一行有序的输出 .
 * 每个输入流的索引都关联着一个元素(输入中的下一个字符) ,
 * 初始化以后 , 程序会删除并打印处队列中的最小字符 , 然后将该输入的下一个字符添加为一个元素 .
 *
 * > echo A B C A B C > m1.txt
 * > echo A B A A D C > m2.txt
 * > echo B B A D A C > m3.txt
 * > java Multiway m1.txt m2.txt m3.txt
 *
 * index 0 : key A
 * index 1 : key A
 * index 0 : key B
 * index 1 : key B
 * index 1 : key A
 * index 1 : key A
 * index 2 : key B
 * index 2 : key B
 * index 2 : key A
 * index 0 : key C
 * index 0 : key A
 * index 0 : key B
 * index 0 : key C
 * index 2 : key D
 * index 2 : key A
 * index 2 : key C
 * index 1 : key D
 * index 1 : key C
 *
 * @Author chenpantao
 * @Date 5/21/21 3:27 PM
 * @Version 1.0
 */
public class Multiway {

    public static void merge(In[] streams) {
        int N = streams.length;
        IndexMinPQ<String> pq = new IndexMinPQ<>(N);

        //为队列中的每一个索引添加一个元素
        for (int i = 0; i < N; i++) {
            if (!streams[i].isEmpty()) {
                pq.insert(i, streams[i].readString());
            }
        }

        //循环从队列中删除最小的元素 , 并返回该元素的索引
        //然后从对应索引的输入流中再取一个元素放入队列
        while (!pq.isEmpty()) {
            String min = pq.min();
            int i = pq.delMin();
            StdOut.printf("index %s : key %s \n", i, min);

            if (!streams[i].isEmpty()) {
                pq.insert(i, streams[i].readString());
            }
        }
    }

    public static void main(String[] args) {
        int N = args.length;
        In[] streams = new In[N];
        for (int i = 0; i < N; i++) {
            streams[i] = new In(args[i]);
        }

        merge(streams);
    }
}
