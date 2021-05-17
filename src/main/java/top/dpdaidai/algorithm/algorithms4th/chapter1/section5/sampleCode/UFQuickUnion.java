package top.dpdaidai.algorithm.algorithms4th.chapter1.section5.sampleCode;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 使用quick-union算法解决动态连通性问题
 * 成长数量级为 N 到 N² 之间 , 对输入有较高的依赖
 *
 * @Author chenpantao
 * @Date 5/16/21 11:28 PM
 * @Version 1.0
 */
public class UFQuickUnion {
    private int[] id;

    private int count;

    public UFQuickUnion(int N) {
        //初始化分量id的数组
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
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
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }

        id[pRoot] = qRoot;
        count--;

    }


    public static void main(String[] args) {
        //解决由StdId得到的动态连通性问题
        int N = StdIn.readInt();
        UFQuickUnion uf = new UFQuickUnion(N);

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) {
                continue;
            }
            uf.union(p, q);

            StdOut.println(p + " " + q);

        }

        StdOut.println(uf.count() + "components");

    }

}
