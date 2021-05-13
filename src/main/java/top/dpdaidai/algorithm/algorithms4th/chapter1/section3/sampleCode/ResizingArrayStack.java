package top.dpdaidai.algorithm.algorithms4th.chapter1.section3.sampleCode;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 *
 * 可自动扩容和可迭代的下压栈数据结构
 *  % more tobe.txt
 *  to be or not to - be - - that - - - is
 *
 * @Author chenpantao
 * @Date 5/13/21 2:19 PM
 * @Version 1.0
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {

    private Item[] a;
    private int N;

    public ResizingArrayStack() {
        a = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        if (N == a.length) {
            resize(N * 2);
        }
        a[N++] = item;
    }

    public int getLength() {
        return a.length;
    }

    public Item pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow error");
        }
        Item item = a[--N];
        a[N] = null;  // 避免对象游离
        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }


    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    //后进先出迭代器
    private class ReverseArrayIterator implements Iterator<Item> {

        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return a[--i];
        }
    }

    public static void main(String[] args) {
        ResizingArrayStack<String> s = new ResizingArrayStack<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) s.push(item);
            else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");

        for (String o : s) {
            StdOut.println(o);
        }

        StdOut.println(s.getLength());
    }

}
