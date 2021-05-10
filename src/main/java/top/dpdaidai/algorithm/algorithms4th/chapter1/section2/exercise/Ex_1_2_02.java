package top.dpdaidai.algorithm.algorithms4th.chapter1.section2.exercise;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 编写一个Interval1D的用例，从命令行接受一个整数N。从标准输入中读取N个间隔（每个间隔由一对double值定义）并打印出所有相交的间隔对。
 * https://www.cnblogs.com/longjin2018/p/9848813.html
 *
 * @Author chenpantao
 * @Date 5/10/21 8:01 PM
 * @Version 1.0
 */
public class Ex_1_2_02 {

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Interval1D[] interArray = new Interval1D[N];
        int index = 0;
        //generate interval1D
        while (!StdIn.isEmpty()) {
            interArray[index] = new Interval1D(StdIn.readDouble(), StdIn.readDouble());
            index++;
            if (index == N) break;
        }//end while

        //find  intersect
        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                //判断是否有交集
                if (interArray[i].intersects(interArray[j]))
                    //格式化输出结果
                    StdOut.printf("%d(%f,%f)  与  %d(%f,%f) 相交\n", i, interArray[i].min(), interArray[i].max(), j, interArray[j].min(), interArray[j].max());
    }

}
