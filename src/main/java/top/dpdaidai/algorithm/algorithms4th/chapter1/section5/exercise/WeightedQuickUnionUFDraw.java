package top.dpdaidai.algorithm.algorithms4th.chapter1.section5.exercise;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * WeightedQuickUnionUF的均摊图像
 * 参考 https://www.cnblogs.com/longjin2018/p/9854799.html 后修改
 * @Author chenpantao
 * @Date 5/17/21 10:18 PM
 * @Version 1.0
 */
public class WeightedQuickUnionUFDraw {

    private int[] id;
    private int[] sz;
    private int count;
    //
    private int connectTimes = 0;
    private int cost = 0;
    private int total = 0;

    public int ConnTimes() {
        return connectTimes;
    }

    public int Cost() {
        return cost;
    }

    public int Total() {
        return total;
    }

    //
    public WeightedQuickUnionUFDraw(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
//            StdOut.printf("%3d", i);
        }
//        StdOut.println();
        //
        sz = new int[N];
        for (int i = 0; i < N; i++)
            sz[i] = 1;
    }

    public int count() {
        return count;
    }

    boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        cost++;
        total++;
        while (p != id[p]) {
            cost += 2;
            total += 2;
            p = id[p];
        }
        return p;
    }


    public void union(int p, int q) {
        connectTimes++;
        cost = 0;

        int i = find(p);
        int j = find(q);
//        StdOut.printf(" i=%d j=%d\n", i, j);
        if (i == j) return;

        cost = cost + 6;
        total = total + 6;
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] = sz[j] + sz[i];
        } else {
            id[j] = i;
            sz[i] = sz[i] + sz[j];
        }
        count--;
    }

    public static void main(String[] qrgs) {
        int N = StdIn.readInt();
        WeightedQuickUnionUFDraw uf = new WeightedQuickUnionUFDraw(N);
        //
        StdDraw.setXscale(0, 1000);
        StdDraw.setYscale(0, 1000);
        StdDraw.setPenRadius(0.005);
        //
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            //union()内部含有connected的判断 , 无需判断
//            if (uf.connected(p, q)) continue;
            StdOut.println(p + " " + q);
            uf.union(p, q);

            //draw
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.point(uf.ConnTimes(), uf.Cost());
            //
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.point(uf.ConnTimes(), uf.Total() / uf.ConnTimes());
        }//end while
    }//end main
}
