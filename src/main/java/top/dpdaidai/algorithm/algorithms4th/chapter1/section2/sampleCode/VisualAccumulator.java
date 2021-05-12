package top.dpdaidai.algorithm.algorithms4th.chapter1.section2.sampleCode;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @Author chenpantao
 * @Date 5/12/21 12:09 PM
 * @Version 1.0
 */
public class VisualAccumulator {

    private double total;
    private int N;

    public VisualAccumulator(int trials, double max) {
        StdDraw.setXscale(0, trials);
        StdDraw.setYscale(0, max);
        StdDraw.setPenRadius(.005);
    }

    public void addDataValue(double val) {
        N++;
        total += val;
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(N, val);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(N, mean());
    }

    public double mean() {
        return total / N;
    }

    public String toString() {
        return "Mean (" + N + " values): " + String.format("%8.5f", mean());
    }

    public static void main(String[] args) {
        int t = Integer.parseInt(args[0]);
        VisualAccumulator a = new VisualAccumulator(t, 1.0);
        for (int i = 0; i < t; i++)
            a.addDataValue(StdRandom.random());
        StdOut.println(a);
    }

}
