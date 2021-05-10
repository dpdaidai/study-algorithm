package top.dpdaidai.algorithm.algorithms4th.chapter1.section2.sampleCode;

import edu.princeton.cs.algs4.*;

/**
 * @Author chenpantao
 * @Date 5/10/21 6:59 PM
 * @Version 1.0
 */
public class Interval2DTest {

    public static void main(String[] args) {
        double xlo = Double.parseDouble(args[0]);
        double xhi = Double.parseDouble(args[1]);
        double ylo = Double.parseDouble(args[2]);
        double yhi = Double.parseDouble(args[3]);

        int T = Integer.parseInt(args[4]);

        Interval1D xInterval = new Interval1D(xlo, xhi);
        Interval1D yInterval = new Interval1D(ylo, yhi);

        //根据间隔确定一个矩形 , 参数1是横向间隔 , 参数2为纵向间隔
        Interval2D box = new Interval2D(xInterval, yInterval);
        box.draw();

        Counter hits = new Counter("hits");
        for (int i = 0; i < T; i++) {
            double x = Math.random();
            double y = Math.random();
            Point2D point2D = new Point2D(x, y);
            if (box.contains(point2D)) {
                hits.increment();
            }else {
                point2D.draw();
            }
        }

        //随机点命中矩形的次数/总次数 , 大致和矩形面积相等
        StdOut.println(hits);
        StdOut.println(box.area());
    }
}
