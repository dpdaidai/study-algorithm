package top.dpdaidai.algorithm.algorithms4th.chapter1.section2.sampleCode;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @Author chenpantao
 * @Date 5/10/21 9:08 PM
 * @Version 1.0
 */
public class Counter {

    private final String name;     // counter name
    private int count;             // current value

    // create a new counter
    public Counter(String id) {
        name = id;
    }

    // increment the counter by 1
    public void increment() {
        count++;
    }

    // return the current count
    public int tally() {
        return count;
    }

    // return a string representation of this counter
    public String toString() {
        return count + " " + name;
    }

    // compare two Counter objects based on their count
    public int compareTo(Counter that) {
        if      (this.count < that.count) return -1;
        else if (this.count > that.count) return +1;
        else                              return  0;
    }


    // test client
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        // create N counters
        Counter[] hits = new Counter[N];
        for (int i = 0; i < N; i++) {
            hits[i] = new Counter("counter" + i);
        }

        // increment T counters at random
        for (int t = 0; t < T; t++) {
            hits[StdRandom.uniform(N)].increment();
        }

        // print results
        for (int i = 0; i < N; i++) {
            StdOut.println(hits[i]);
        }
    }


}
