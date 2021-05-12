package top.dpdaidai.algorithm.algorithms4th.chapter1.section2.sampleCode;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

/**
 * 将所有输入文件内容输出到最后一个文件中
 *
 * @Author chenpantao
 * @Date 5/10/21 8:59 PM
 * @Version 1.0
 */
public class Cat {

    public static void main(String[] args) {
        Out out = new Out(args[args.length - 1]);

        for (int i = 0; i < args.length - 1; i++) {
            In in = new In(args[i]);
            String s = in.readAll();
            out.println(s);
            in.close();
        }
        out.close();

    }

}
