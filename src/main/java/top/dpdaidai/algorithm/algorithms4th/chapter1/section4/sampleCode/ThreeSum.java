package top.dpdaidai.algorithm.algorithms4th.chapter1.section4.sampleCode;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 *
 * 举例在数据量成倍增长时 , 我们能否预测程序运行时间的增加速度
 *
 * 1000个数
 * java ThreeSum 1Kints.txt
 * elapsed time = 0.263
 * 70
 *
 * 2000个数
 * java ThreeSum 2Kints.txt
 * elapsed time = 2.105
 * 528
 *
 * 4000个数
 * java ThreeSum 4Kints.txt
 *elapsed time = 16.286
 * 4039
 *
 * 8000个数
 * java ThreeSum 8Kints.txt
 * elapsed time = 133.344
 * 32074
 *
 * @Author chenpantao
 * @Date 5/13/21 7:26 PM
 * @Version 1.0
 */
public class ThreeSum {

    // return number of distinct triples (i, j, k) such that a[i] + a[j] + a[k] = 0
    public static int count(int[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                for (int k = j+1; k < N; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args)  {
        int[] a = In.readInts(args[0]);

        Stopwatch timer = new Stopwatch();
        int cnt = count(a);
        StdOut.println("elapsed time = " + timer.elapsedTime());
        StdOut.println(cnt);
    }

}
