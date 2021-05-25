package top.dpdaidai.algorithm.algorithms4th.chapter3.section2.exercise;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import top.dpdaidai.algorithm.algorithms4th.chapter1.section3.sampleCode.Queue;

/**
 *
 * 分析 二叉查找树生成的符号表 , put() 操作的平均比较次数 . 不计算contains() , 忽略其成本
 * 1.   灰点为每次比较的次数
 * 2.   红点为平均比较的次数
 *
 * > java BSTDraw 8 < tale.txt
 * sum/times : 13.171058134671686
 * size 5126
 * time consume : 119.987
 * business 122
 *
 * 预测 比较次数 = 2lnN = 1.39lgN = 1.39 * lg5126 = 17.19
 * 实际 平均比较次数 = 13.17
 *
 * @Author chenpantao
 * @Date 5/25/21 6:16 PM
 * @Version 1.0
 */
public class BSTDraw<Key extends Comparable<Key>, Value> {

    private class Node {

        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int N;

        public Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }

    }

    private Node root;

    private int count;

    public boolean isEmpty() {
        return root.N == 0;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public int count() {
        return count;
    }

    public int size() {
        return size(root);
    }

    public int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.N;
    }

    //二分查找键
    public Value get(Key key) {
        return get(root, key);

    }

    public Value get(Node x, Key key) {

        //递归结束两个标志
        //1  命中key , 返回value
        //2  未命中key , 指向一个空链接 , 返回null
        if (x == null) {
            return null;
        }
        int compare = key.compareTo(x.key);
        if (compare > 0)
            return get(x.right, key);
        else if (compare < 0)
            return get(x.left, key);
        else
            return x.value;

    }

    public void put(Key key, Value value) {
        count = 0;
        //在递归中重置了引用 , 实际上大部分引用不会发生改变
        root = put(root, key, value);
    }

    public Node put(Node x, Key key, Value value) {
        //递归结束两个标志
        //1  命中key , 更新value
        //2  未命中key , 更新空链接
        count++;
        if (x == null) {
            return new Node(key, value, 1);
        }
        int compare = key.compareTo(x.key);
        if (compare > 0) x.right = put(x.right, key, value);
        else if (compare < 0) x.left = put(x.left, key, value);
        else x.value = value;

        //当compare=0时 , 子树增加了节点 , 那么父节点也需要更新N
        x.N = size(x.left) + size(x.right) + 1;
        return x;

    }

    public Key min() {
        if (isEmpty()) return null;
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    public Key max() {
        if (isEmpty()) return null;
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else return max(x.right);
    }


    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }


    public static void main(String[] args) {
        int minLength = Integer.parseInt(args[0]);
        BSTDraw<String, Integer> st = new BSTDraw<>();
        int sum = 0;
        int times = 0;
        StdDraw.setXscale(0, 14350);
        StdDraw.setYscale(0, 20);
        StdDraw.setPenRadius(0.005);

        Stopwatch stopwatch = new Stopwatch();

        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < minLength) {
                continue;
            }
            if (!st.contains(word)) st.put(word, 1);
            else st.put(word, st.get(word) + 1);
            int count = st.count();
            sum += count;
            times++;

            //draw
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.point(times, count);
            //
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.point(times, (sum + 0.0) / times);
        }

        StdOut.println("sum/times : " + (sum + 0.0) / times);

        StdOut.println("size " + st.size());

        StdOut.println("time consume : " + stopwatch.elapsedTime());

        String max = " ";
        st.put(max, 0);
        for (String s : st.keys()) {
            if (st.get(s) > st.get(max)) {
                max = s;
            }
        }

        StdOut.println(max + " " + st.get(max));


    }


}
