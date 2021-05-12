package top.dpdaidai.algorithm.algorithms4th.chapter1.section3.sampleCode;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * 使用bag数据类型 , 计算表中输入中所有double值的平均值和样本标准差
 *
 * @Author chenpantao
 * @Date 5/12/21 4:25 PM
 * @Version 1.0
 */
public class Stats {

    public static void main(String[] args) {
        Bag<Double> numbers = new Bag<>();
        while (!StdIn.isEmpty()) {
            numbers.add(StdIn.readDouble());
        }

        int N = numbers.size();
        double sum = 0;

        for (Double number : numbers) {
            sum += number;
        }

        double mean = sum / N;

        sum = 0.0;
        for (Double number : numbers) {
            sum += (number - mean) * (number - mean);
        }

        double sqrt = Math.sqrt(sum / (N - 1));
        StdOut.printf("Mean : %.2f\n", mean);
        StdOut.printf("Std dev : %.2f\n", sqrt);


    }

}
