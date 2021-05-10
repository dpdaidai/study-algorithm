package top.dpdaidai.algorithm.algorithms4th.chapter1.section1.sampleCode;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 练习格式化输出
 * 由于书中许多算法需要测试结果 , 那么和标准输出相比 , 输入源是一致的才能有对比作用
 * 所以许多类库的测试都使用了algs4-data中的数据 , 或者手动输入测试数据
 * 使用时通过命令行指定输入源 , 例如 : java RandomSeq 5 100.0 200.0
 *
 * @Author chenpantao
 * @Date 5/10/21 12:25 PM
 * @Version 1.0
 */
public class RandomSeq {

    /**
     * StdOut使用示例
     * @param args : 参数1 : 打印个数 , 参数2 : 值下限 , 参数3 : 值上线
     */
    public static void main(String[] args) {
        //打印N个(lo,hi)之间的随机值
        int n = Integer.parseInt(args[0]);
        double lo = Double.parseDouble(args[1]);
        double hi = Double.parseDouble(args[2]);
        for (int i = 0; i < n; i++) {
            double uniform = StdRandom.uniform(lo, hi);
            StdOut.printf("%.3f\n",uniform);
        }
    }

}
