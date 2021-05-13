package top.dpdaidai.algorithm.algorithms4th.chapter1.section3.sampleCode;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * 下压堆栈 ( 链表实现 )
 * @Author chenpantao
 * @Date 5/13/21 3:30 PM
 * @Version 1.0
 */

public class Stack<Item> implements Iterable<Item> {

    private class Node {
        private Item item;
        private Node next;
    }

    private Node first;
    private int N;

    public Stack() {
        first = null;
        N = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        Node oldFirst = this.first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public Item pop() {
        if (isEmpty()) throw new RuntimeException("Stack underflow");
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public Item peek() {
        if (isEmpty()) throw new RuntimeException("Stack underflow");
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

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Stack<String> s = new Stack<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) s.push(item);
            else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");

        StdOut.println(s);

        for (String s1 : s) {
            StdOut.println(s1);
        }

    }


}
