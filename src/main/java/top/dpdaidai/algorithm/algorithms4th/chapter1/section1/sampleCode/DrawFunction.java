package top.dpdaidai.algorithm.algorithms4th.chapter1.section1.sampleCode;

import edu.princeton.cs.algs4.StdDraw;

/**
 *
 * 绘制函数
 *      1  y = x;
 *      2  y = x²
 *      3  y = x * log(x)
 *
 * @Author chenpantao
 * @Date 5/10/21 2:18 PM
 * @Version 1.0
 */
public class DrawFunction {

    public static void main(String[] args) {
        int N = 100;
        //设置 直线 x 轴的范围
        StdDraw.setXscale(0, N);
        //设置 直线 y 轴的范围
        StdDraw.setYscale(0, N * N);
        //设置线的粗细半径 . 实际上这里的绘图就是在密集的打点 , 形成线条
        StdDraw.setPenRadius(0.01);
        for (int i = 1; i <= N; i++) {
            // y = x
            StdDraw.point(i, i);
            // y = x²
            StdDraw.point(i, i * i);
            // y = x * log(x)
            StdDraw.point(i, i * Math.log(i));
        }

    }
}
