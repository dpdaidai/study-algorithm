package top.dpdaidai.algorithm.algorithms4th.chapter3.section1.sampleCode;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * 从标准输入种得到一列指定最短长度的字符串, 并记录每个字符串出现的次数, 然后遍历所有键并找出出现频率最高的键.
 *
 * @Author chenpantao
 * @Date 5/24/21 8:26 PM
 * @Version 1.0
 */
public class FrequencyCounter {

    public static void main(String[] args) {
        int minLength = Integer.parseInt(args[0]);
        ST<String, Integer> st = new ST<>();
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < minLength) {
                continue;
            }
            if (!st.contains(word)) st.put(word, 1);
            else st.put(word, st.get(word) + 1);
        }
        StdOut.println("size " + st.size());

        String max = " ";
        st.put(max, 0);
        for (String s : st) {
            if (st.get(s) > st.get(max)) {
                max = s;
            }
        }

        StdOut.println(max + " " + st.get(max));

    }
}
