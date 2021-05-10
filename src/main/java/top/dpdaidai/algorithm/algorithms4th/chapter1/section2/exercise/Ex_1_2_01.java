package top.dpdaidai.algorithm.algorithms4th.chapter1.section2.exercise;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.2.1编写一个Point2D的用例 , 编写一个Point2D的用例，从命令行接受一个整数N。在单位正方形中生成N个随机点，然后计算两点之间的最近距离。
 * https://www.cnblogs.com/longjin2018/p/9848788.html
 *
 * @Author chenpantao
 * @Date 5/10/21 7:36 PM
 * @Version 1.0
 */
public class Ex_1_2_01 {

    public static void main(String[] args) {

        int N = Integer.parseInt(args[0]);

        //坐标范围
        double x0 = 0.0;
        double x1 = 200.0;
        double y0 = 0.0;
        double y1 = 200.0;
        StdDraw.setXscale(x0, x1);
        StdDraw.setYscale(y0, y1);
        StdDraw.setPenRadius(0.005);

        //generate points and draw point.
        Point2D[] pointArray = new Point2D[N];
        for (int i = 0; i < N; i++) {
            Point2D p = new Point2D(StdRandom.uniform(x0, x1), StdRandom.uniform(y0, y1));
            pointArray[i] = p;
            pointArray[i].draw();
        }

        if (N < 2) return;
        if (N == 2) {
            StdOut.printf("The two point is shortes.");
            return;
        }

        //find the shortest two point.
        Point2D p1 = pointArray[0];
        Point2D p2 = pointArray[0];
        double shortestDist = Math.sqrt(200 * 200 + 200 * 200) + 1.0;
        double dist;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                dist = pointArray[i].distanceTo(pointArray[j]);
                if (shortestDist > dist) {
                    shortestDist = dist;
                    p1 = pointArray[i];
                    p2 = pointArray[j];
                }//end if
            }//end for j
        }//end for i
        StdDraw.setPenColor(StdDraw.RED);
        p1.draw();
        p2.draw();

    }
}
