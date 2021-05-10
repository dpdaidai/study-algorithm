package top.dpdaidai.algorithm.algorithms4th.chapter1.section2.exercise;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.Interval2D;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 *
 * 编写一个Interval2D用例，从命令行接受参数N、min和max。生成N个随机的2D间隔，其宽和高均匀地分布在单位正方形中的min和max之间。
 * 用StdDraw画出它们并打印出相关的间隔对的数量以及有包含关系的间隔对数量。
 * https://www.cnblogs.com/longjin2018/p/9848817.html
 *
 * @Author chenpantao
 * @Date 5/10/21 8:10 PM
 * @Version 1.0
 */
public class Ex_1_2_03 {

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        double min = Double.parseDouble(args[1]);
        double max = Double.parseDouble(args[2]);
        Interval2D[] inter2dArray = new Interval2D[N];
        //
        double lo;
        double hi;
        //generate interval2D
        for (int i = 0; i < N; i++) {
            lo = StdRandom.uniform(min, max);
            hi = StdRandom.uniform(lo, max);
            Interval1D x = new Interval1D(lo, hi);
            //
            lo = StdRandom.uniform(min, max);
            hi = StdRandom.uniform(lo, max);
            Interval1D y = new Interval1D(lo, hi);
            //
            inter2dArray[i] = new Interval2D(x, y);
            inter2dArray[i].draw();
        }

        //find  intersect and contains
        int intersectCount = 0;
        int containCount = 0;
        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++) {
                //intersect
                if (inter2dArray[i].intersects(inter2dArray[j]))
                    intersectCount++;
                //contains: intersect and contains three top point
//                if (
//                        (inter2dArray[i].contains(inter2dArray[j].leftTopCorner())  &&
//                                inter2dArray[i].contains(inter2dArray[j].leftBottom()) &&
//                                inter2dArray[i].contains(inter2dArray[j].rightTopCorner())
//                        ) ||
//                                (inter2dArray[j].contains(inter2dArray[i].leftTopCorner())  &&
//                                        inter2dArray[j].contains(inter2dArray[i].leftBottom()) &&
//                                        inter2dArray[j].contains(inter2dArray[i].rightTopCorner())
//                                )
//                )
//                    containCount++;
            }
        StdOut.printf("intersectCount=%d,containCount=%d", intersectCount, containCount);


    }
}
