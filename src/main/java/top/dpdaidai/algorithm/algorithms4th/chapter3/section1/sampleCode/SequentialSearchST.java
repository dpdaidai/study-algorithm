package top.dpdaidai.algorithm.algorithms4th.chapter3.section1.sampleCode;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import top.dpdaidai.algorithm.algorithms4th.chapter1.section3.sampleCode.Queue;

/**
 *
 * 无序链表实现符号表
 *
 *
 * @Author chenpantao
 * @Date 5/24/21 8:52 PM
 * @Version 1.0
 */
public class SequentialSearchST<Key, Value> {

    //内部类
    private class Node {
        //保存本节点的键值对
        Key key;
        Value value;
        //保存下一个节点的引用
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    //本节点键值对
    private Node first;

    //当前符号表大小
    private int N;

    //查找值, 遍历链表
    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            //命中key
            if (x.key.equals(key)) return x.value;
        }
        return null;
    }

    public void put(Key key, Value value) {
        //防御性代码
        if (value == null) {
            delete(key);
            return;
        }

        //遍历节点
        for (Node x = first; x != null; x = x.next) {
            //命中key , 更新value
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        }

        //为在命中key , 新增节点
        first = new Node(key, value, first);

        N++;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    //返回一个可迭代的类型
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (Node x = first; x != null; x = x.next) {
            queue.enqueue(x.key);
        }
        return queue;
    }

    //个人实现
    public void delete(Key key) {

        if (first.key.equals(key)) {
            N--;
            first = first.next;
            return;
        }

        Node current = first;
        while (current.next != null) {
            if (current.next.key.equals(key)) {
                N--;
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }

    }


    //示例实现
//    public void delete(Key key) {
//        first = delete(first, key);
//    }
//
//    public Node delete(Node x, Key key) {
//        if (x == null) return null;
//        if (x.key.equals(key)) {
//            N--;
//            return x.next;
//        }
//        x.next = delete(x.next,key);
//        return x;
//    }

    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
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

        st.delete("L");

        for (String s : st.keys())
            StdOut.print(s + " " + st.get(s)+" ");
        StdOut.println();

        st.delete("S");

        for (String s : st.keys())
            StdOut.print(s + " " + st.get(s)+" ");
        StdOut.println();

        StdOut.println("size : " + st.size());

    }

}
