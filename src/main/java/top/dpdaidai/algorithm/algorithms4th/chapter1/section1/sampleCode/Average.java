package top.dpdaidai.algorithm.algorithms4th.chapter1.section1.sampleCode;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 使用StdIn输入数字 , 结束输入时求和并打印出来
 *
 * 在/Users/chenpantao/StudyCode/algorithm/study-algorithm/src/main/java 目录下使用如下命令启动
 *
 * javac top/dpdaidai/algorithm/algorithms4th/chapter1/section1/sampleCode/Average.java
 * java top.dpdaidai.algorithm.algorithms4th.chapter1.section1.sampleCode.Average
 *
 * @Author chenpantao
 * @Date 5/10/21 1:39 PM
 * @Version 1.0
 */
public class Average {

    public static void main(String[] args) {
        //取StdIn中所有数的平均值
        double sum = 0.0;
        int count = 0;
        while (!StdIn.isEmpty()) {
            sum += StdIn.readDouble();
            count++;
        }
        double avg = sum / count;
        StdOut.printf("Average is %.5f\n", avg);

    }


}
