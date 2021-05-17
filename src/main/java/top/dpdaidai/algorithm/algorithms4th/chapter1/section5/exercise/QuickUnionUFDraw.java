package top.dpdaidai.algorithm.algorithms4th.chapter1.section5.exercise;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * 绘制QuickUnionUF的均摊成本图像
 * 参考 https://www.cnblogs.com/longjin2018/p/9854799.html 后修改
 *
 * @Author chenpantao
 * @Date 5/17/21 10:11 PM
 * @Version 1.0
 */
public class QuickUnionUFDraw {
    private int[] id;
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
    public QuickUnionUFDraw(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
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

            //赋值访问一次 , 比对访问一次
            cost += 2;
            total += 2;
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q) {

        connectTimes++;
        cost = 0;

        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;

        cost++;
        total++;
        id[pRoot] = qRoot;

        count--;

    }

    public static void main(String[] qrgs) {
        int N = StdIn.readInt();
        QuickUnionUFDraw uf = new QuickUnionUFDraw(N);
        StdDraw.setXscale(0, 1000);
        StdDraw.setYscale(0, 1000);
        StdDraw.setPenRadius(0.005);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            ////union()内部含有connected的判断 , 无需判断
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
