package top.dpdaidai.algorithm.algorithms4th.chapter1.section3.sampleCode;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * 背包的实现
 *  1.  只进不出
 *  2.  可迭代
 *
 * @Author chenpantao
 * @Date 5/13/21 4:45 PM
 * @Version 1.0
 */
public class Bag<Item> implements Iterable<Item> {

    private int N;         // number of elements in bag
    private Node first;    // beginning of bag

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
    }

    public Bag() {
        first = null;
        N = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void add(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
