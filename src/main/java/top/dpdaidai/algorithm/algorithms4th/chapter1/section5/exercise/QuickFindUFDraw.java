package top.dpdaidai.algorithm.algorithms4th.chapter1.section5.exercise;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * 绘制QuickFindUF的均摊成本图像
 * 参考 https://www.cnblogs.com/longjin2018/p/9854799.html 后修改
 *
 * @Author chenpantao
 * @Date 5/17/21 9:48 PM
 * @Version 1.0
 */
public class QuickFindUFDraw {

    private int[] id;
    private int count;

    //当前连接次数
    private int connectTimes = 0;
    //本次连接访问数组元素次数
    private int cost = 0;
    //总共访问数组元素次数
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
    public QuickFindUFDraw(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        cost = 2;
        total = total + 2;
        return find(p) == find(q);
    }

    public int find(int p) {
        cost++;
        total++;
        return id[p];
    }

    public void union(int p, int q) {
        cost = 0;
        connectTimes++;

        int pID = find(p);
        int qID = find(q);

        if (pID == qID) return;

        for (int i = 0; i < id.length; i++) {

            //if条件访问一次数组元素
            cost++;
            total++;
            if (id[i] == pID) {
                //赋值访问一次数组元素
                cost++;
                total++;
                id[i] = qID;
            }
        }
        count--;
    }


    public static void main(String[] qrgs) {
        int N = StdIn.readInt();
        QuickFindUFDraw uf = new QuickFindUFDraw(N);
        StdDraw.setXscale(0, 1000);
        StdDraw.setYscale(0, 1000);
        StdDraw.setPenRadius(0.005);
        while (!StdIn.isEmpty()) {

            int p = StdIn.readInt();
            int q = StdIn.readInt();

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
