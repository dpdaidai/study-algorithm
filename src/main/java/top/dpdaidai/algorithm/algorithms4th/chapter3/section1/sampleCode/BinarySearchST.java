package top.dpdaidai.algorithm.algorithms4th.chapter3.section1.sampleCode;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import top.dpdaidai.algorithm.algorithms4th.chapter1.section3.sampleCode.Queue;

/**
 * @Author chenpantao
 * @Date 5/25/21 1:28 PM
 * @Version 1.0
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {

    private Key[] keys;

    private Value[] values;

    private int N;

    private static final int INIT_CAPACITY = 2;

    public BinarySearchST() {
        this(INIT_CAPACITY);
    }

    public BinarySearchST(int capacity) {
        this.keys = (Key[]) new Comparable[capacity];
        this.values = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private void resize(int capacity) {
        assert capacity >= N;
        Key[] tempk = (Key[]) new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            tempk[i] = keys[i];
            tempv[i] = values[i];
        }
        values = tempv;
        keys = tempk;
    }

    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }

        int i = rank(key);
        //判断是否命中key
        if (i < N && keys[i].compareTo(key) == 0) {
            return values[i];
        }
        return null;
    }

    //有序keys中, 二分查找小于key的数量
    public int rank(Key key) {
        int lo = 0;
        int hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int i = key.compareTo(keys[mid]);
            if (i < 0) hi = mid - 1;
            else if (i > 0) lo = mid + 1;
            else return mid;
        }
        //未命中key时 , 返回keys中小于key的数量
        return lo;
    }

    public void put(Key key, Value value) {
        int i = rank(key);
        //判断是否命中key
        if (i < N && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }

        //判断是否需要扩容
        if (N == keys.length) resize(2 * keys.length);

        //不包含key , 则在i处插入新的键值对
        //先将大于i的值都向后挪动
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }

        keys[i] = key;
        values[i] = value;
        N++;
    }

    public void delete(Key key) {
        if (isEmpty()) {
            return;
        }

        int rank = rank(key);

        //未命中key
        if (rank >= N || key.compareTo(keys[rank]) != 0) {
            return;
        }

        //移动数组
        for (int i = rank; i < N - 1; i++) {
            keys[i] = keys[i + 1];
            values[i] = values[i + 1];
        }

        keys[N - 1] = null;
        values[N - 1] = null;

        N--;

        //判断是否需要缩容
        if (N > 0 && N == keys.length / 4) resize(keys.length / 2);
    }


    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Key min() {
        if (isEmpty()) return null;
        return keys[0];
    }

    public Key max() {
        if (isEmpty()) return null;
        return keys[N - 1];
    }

    public void deleteMin() {
        if (isEmpty()) throw new RuntimeException("Symbol table underflow error");
        delete(min());
    }

    public void deleteMax() {
        if (isEmpty()) throw new RuntimeException("Symbol table underflow error");
        delete(max());
    }

    public Key select(int k) {
        if (k < 0 || k >= N) {
            return null;
        }
        return keys[k];
    }


    public Key floor(Key key) {
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0) return keys[i];
        if (i == 0) return null;
        else return keys[i - 1];
    }

    public Key ceiling(Key key) {
        int i = rank(key);
        if (i == N) return null;
        else return keys[i];
    }

    public int size(Key lo, Key hi) {
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        if (lo == null && hi == null) return queue;
        if (lo == null) throw new RuntimeException("lo is null in keys()");
        if (hi == null) throw new RuntimeException("hi is null in keys()");
        if (lo.compareTo(hi) > 0) return queue;
        for (int i = rank(lo); i < rank(hi); i++) {
            queue.enqueue(keys[i]);
        }
        if (contains(hi)) {
            queue.enqueue(keys[rank(hi)]);
        }
        return queue;
    }

    private boolean isSorted() {
        for (int i = 1; i < size(); i++)
            if (keys[i].compareTo(keys[i - 1]) < 0) return false;
        return true;
    }

    private boolean rankCheck() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (int i = 0; i < size(); i++)
            if (keys[i].compareTo(select(rank(keys[i]))) != 0) return false;
        return true;
    }

    private boolean check() {
        return isSorted() && rankCheck();
    }

    public static void main(String[] args) {
        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.print(s + " " + st.get(s)+" ");
        StdOut.println();

        st.delete("X");

        for (String s : st.keys())
            StdOut.print(s + " " + st.get(s)+" ");
        StdOut.println();

        st.delete("A");

        for (String s : st.keys())
            StdOut.print(s + " " + st.get(s)+" ");
        StdOut.println();

        st.delete("L");

        for (String s : st.keys())
            StdOut.print(s + " " + st.get(s)+" ");
        StdOut.println();

        StdOut.println("size : " + st.size());

        StdOut.println("check : " + st.check());

    }
}
