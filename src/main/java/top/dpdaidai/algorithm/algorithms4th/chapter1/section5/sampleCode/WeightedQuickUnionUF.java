package top.dpdaidai.algorithm.algorithms4th.chapter1.section5.sampleCode;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 *
 * 加权quick-union算法解决动态连通性问题
 * 加权记录分量的树大小 , 每次将小树连接到大树上
 *
 * @Author chenpantao
 * @Date 5/17/21 12:26 AM
 * @Version 1.0
 */
public class WeightedQuickUnionUF {
    private int[] id;

    //在分量根节点坐标处记录树的大小
    private int[] sz;

    private int count;

    public WeightedQuickUnionUF(int N) {
        count = N;
        //初始化分量id的数组
        id = new int[N];
        //初始化各个分量的高度
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }

    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q) {
        //左侧树根坐标
        int pRoot = find(p);
        //右侧树根坐标
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }

        //将小树的根节点连接到大树的根节点
        if (sz[pRoot] < sz[qRoot]) {
            //小树连接到大树
            id[pRoot] = qRoot;
            //更新大树的大小
            sz[qRoot] += sz[pRoot];
        } else {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }

        count--;

    }


    public static void main(String[] args) {
        //解决由StdId得到的动态连通性问题
        int N = StdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);

        Stopwatch timer = new Stopwatch();
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) {
                continue;
            }
            uf.union(p, q);

//            StdOut.println(p + " " + q);

        }

        StdOut.println("time consume : " + timer.elapsedTime());

        StdOut.println(uf.count() + "components");

    }
}
