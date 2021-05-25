package top.dpdaidai.algorithm.algorithms4th.chapter3.section1.exercise;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import top.dpdaidai.algorithm.algorithms4th.chapter1.section3.sampleCode.Queue;

/**
 *
 * 分析 无序链表生成的符号表 , 查找和插入使用的比较次数 和 平均比较次数 . 不计算contains() , 忽略其成本
 * 1.   灰点为每次比较的次数
 * 2.   红点为平均比较的次数
 *
 * > java SequentialSearchSTDraw 8 < tale.txt
 *
 * @Author chenpantao
 * @Date 5/25/21 12:03 PM
 * @Version 1.0
 */
public class SequentialSearchSTDraw<Key, Value> {//内部类

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

    //本次操作比较次数
    private int count;

    //查找值, 遍历链表
    public Value get(Key key) {
        count = 0;
        for (Node x = first; x != null; x = x.next) {
            count++;
            //命中key
            if (x.key.equals(key)) return x.value;
        }
        return null;
    }

    public void put(Key key, Value value) {
        count = 0;
        //防御性代码
        if (value == null) {
            delete(key);
            return;
        }

        //遍历节点
        for (Node x = first; x != null; x = x.next) {
            count++;
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

    public int count() {
        return count;
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

    public static void main(String[] args) {
        int minLength = Integer.parseInt(args[0]);
        SequentialSearchSTDraw<String, Integer> st = new SequentialSearchSTDraw<>();
        int sum = 0;
        int times = 0;
        StdDraw.setXscale(0, 14350);
        StdDraw.setYscale(0, 5737);
        StdDraw.setPenRadius(0.005);

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
            StdDraw.point(times, sum / times);
        }


        StdOut.println("size " + st.size());

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
