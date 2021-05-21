package top.dpdaidai.algorithm.algorithms4th.chapter2.section4.sampleCode;

/**
 * 基于堆的优先队列
 *
 * @Author chenpantao
 * @Date 5/21/21 12:52 PM
 * @Version 1.0
 */
public class MaxPQ<Key extends Comparable> {

    private Key[] pq;

    private int N = 0;


    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        N++;
        pq[N] = v;
        swim(N);
    }

    public Key delMax() {
        exch(1, N);
        Key v = pq[N];
        pq[N] = null;
        N--;
        sink(1);
        return v;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) {
                j = j + 1;
            }

            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }
}
