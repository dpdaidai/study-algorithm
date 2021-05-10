package top.dpdaidai.algorithm.algorithms4th.chapter1.section1.sampleCode;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * 绘制随机数组 和 排序后的数组
 *
 * @Author chenpantao
 * @Date 5/10/21 2:38 PM
 * @Version 1.0
 */
public class DrawRandomArray {

    public static void main(String[] args) {

        int n = 50;
        double[] a = new double[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.random();
        }

        // 绘制排序后的数组
        // Arrays.sort(a);

        for (int i = 0; i < n; i++) {
            double x = 1.0 * i / n;
            double y = a[i] / 2.0;
            double rw = 0.5 / n;
            double rh = a[i] / 2.0;
            //画出一个矩形，分别对应的是，矩形中心点、半宽度、半长度
            StdDraw.filledRectangle(x, y, rw, rh);
        }

    }


}
