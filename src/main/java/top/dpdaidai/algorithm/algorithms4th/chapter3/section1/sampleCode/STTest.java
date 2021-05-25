package top.dpdaidai.algorithm.algorithms4th.chapter3.section1.sampleCode;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 用于跟踪算法的行为 , 测试符号表在put行为下内部的实现
 * 1.  对于符号表的简单实现(无序) , 用例的输出中键的顺序是不确定的
 * 2.  对于有序符号表, 用例应该将键按顺序打印出来
 *
 * > echo t.txt
 * S O R T E X A M P L E
 *
 * > java STTest < t.txt
 * S 0
 * O 1 S 0
 * O 1 R 2 S 0
 * O 1 R 2 S 0 T 3
 * E 4 O 1 R 2 S 0 T 3
 * E 4 O 1 R 2 S 0 T 3 X 5
 * A 6 E 4 O 1 R 2 S 0 T 3 X 5
 * A 6 E 4 M 7 O 1 R 2 S 0 T 3 X 5
 * A 6 E 4 M 7 O 1 P 8 R 2 S 0 T 3 X 5
 * A 6 E 4 L 9 M 7 O 1 P 8 R 2 S 0 T 3 X 5
 * A 6 E 10 L 9 M 7 O 1 P 8 R 2 S 0 T 3 X 5
 *
 * @Author chenpantao
 * @Date 5/24/21 8:16 PM
 * @Version 1.0
 */
public class STTest {

    public static void main(String[] args) {
        ST<String, Integer> st;
        st = new ST<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);

            for (String s : st) {
                StdOut.print(s + " " + st.get(s) + " ");
            }
            StdOut.println();
        }

        for (String s : st) {
            StdOut.println(s + " " + st.get(s));
        }

    }
}
