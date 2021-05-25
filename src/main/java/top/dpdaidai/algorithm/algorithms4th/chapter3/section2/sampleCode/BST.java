package top.dpdaidai.algorithm.algorithms4th.chapter3.section2.sampleCode;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import top.dpdaidai.algorithm.algorithms4th.chapter1.section3.sampleCode.Queue;

/**
 *
 * 基于二叉查找树的符号表
 * 它将插入和查找操作的增长数量级都降低到 1.39lgN
 *
 *
 * @Author chenpantao
 * @Date 5/25/21 5:48 PM
 * @Version 1.0
 */
public class BST<Key extends Comparable<Key>, Value> {

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

    public boolean isEmpty() {
        return root.N == 0;
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
        //在递归中重置了引用 , 实际上大部分引用不会发生改变
        root = put(root, key, value);
    }

    public Node put(Node x, Key key, Value value) {
        //递归结束两个标志
        //1  命中key , 更新value
        //2  未命中key , 更新空链接
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
        else                return min(x.left);
    }

    public Key max() {
        if (isEmpty()) return null;
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else                 return max(x.right);
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
        BST<String, Integer> st = new BST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.print(s + " " + st.get(s)+" ");
        StdOut.println();

//        st.delete("X");
//
//        for (String s : st.keys())
//            StdOut.print(s + " " + st.get(s)+" ");
//        StdOut.println();
//
//        st.delete("A");
//
//        for (String s : st.keys())
//            StdOut.print(s + " " + st.get(s)+" ");
//        StdOut.println();
//
//        st.delete("L");
//
//        for (String s : st.keys())
//            StdOut.print(s + " " + st.get(s)+" ");
//        StdOut.println();

        StdOut.println("size : " + st.size());

//        StdOut.println("check : " + st.check());

    }

}
