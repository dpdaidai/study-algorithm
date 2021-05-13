package top.dpdaidai.algorithm.algorithms4th.chapter1.section3.sampleCode;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * 使用链表实现队列
 *
 * @Author chenpantao
 * @Date 5/13/21 4:15 PM
 * @Version 1.0
 */
public class Queue<Item> implements Iterable<Item> {

    private int N;         // number of elements on queue
    private Node first;    // beginning of queue
    private Node last;     // end of queue

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
    }

    public Queue() {
        first = null;
        last = null;
        N = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void enqueue(Item item) {
        Node node = new Node();
        node.item = item;
        if (isEmpty()) {
            first = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }
        N++;
    }

    public Item dequeue() {
        if (isEmpty()) throw new RuntimeException("Queue underflow");
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    public Item peek() {
        if (isEmpty()) throw new RuntimeException("Queue underflow");
        return first.item;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Queue<String> q = new Queue<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) q.enqueue(item);
            else if (!q.isEmpty()) StdOut.print(q.dequeue() + " ");
        }
        StdOut.println("(" + q.size() + " left on queue)");

        StdOut.println(q);

        for (String s1 : q) {
            StdOut.println(s1);
        }
    }

}
