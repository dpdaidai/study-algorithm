package top.dpdaidai.algorithm.algorithms4th.chapter2.section4.sampleCode;

/**
 *
 * 索引优先队列
 * 相比于优先队列
 * 可根据索引查找元素和删除元素
 *
 * @Author chenpantao
 * @Date 5/21/21 2:51 PM
 * @Version 1.0
 */
public class IndexMinPQ<Key extends Comparable> {

    //pq中元素的数量
    private int N;

    //索引二叉堆 , 从1开始
    //实际意义 : pq 为有序堆 , 存放的是元素的索引
    private int[] pq;

    //逆序 : qp[pq[i]]=pq[qp[i]]=i
    //实际意义 : qp的index就是索引 , 它存放的是元素在pq中的index
    private int[] qp;

    //元素存放点
    private Key[] keys;

    //初始化优先队列
    public IndexMinPQ(int maxN) {
        keys = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        for (int i = 0; i < qp.length; i++) {
            qp[i] = -1;
        }
    }

    //比较两个元素的大小
    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    //交换队列中的两个元素
    //需要同步逆序数组
    public void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;

        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    //上浮
    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    //下沉
    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    //检查是否包含k索引的元素
    public boolean contains(int k) {
        return qp[k] != -1;
    }

    //插入一个索引为k的元素
    public void insert(int k, Key key) {
        N++;
        pq[N] = k;
        qp[k] = N;

        keys[k] = key;
        swim(N);
    }

    //返回最小元素的索引
    public int minIndex(){
        return pq[1];
    }

    //取出队列中最小的元素
    public Key min() {
        return keys[pq[1]];
    }

    //删除队列中最小的元素
    //并返回索引
    public int delMin() {
        int indexOfMin = pq[1];
        exch(1, N);
        qp[pq[N]] = -1;
        keys[pq[N]] = null;
        N--;
        sink(1);
        return indexOfMin;
    }

    //修改索引为k的元素
    //修改后破坏了堆的稳定性 , 需要恢复稳定 , swim和sink只会执行其中之一
    public void change(int k, Key key) {
        keys[k] = key;
        swim(qp[k]);
        sink(qp[k]);
    }

    //删除索引为k的元素
    //修改后破坏了堆的稳定性 , 需要恢复稳定 , swim和sink只会执行其中之
    public void delete(int k) {
        int index = qp[k];
        exch(index, N);
        N--;
        swim(index);
        sink(index);
        keys[k] = null;
        qp[k] = -1;
    }

}
