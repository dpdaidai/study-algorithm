package top.dpdaidai.algorithm.algorithms4th.chapter1.section4.sampleCode;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 *
 * 3-sum简化问题 -- 2-sum
 * 找出一个输入文件中所有和为0的整数对的数量 , 简单起见 , 假设所有整数各不相同 .
 * 解法1 : 双层for循环 , T(N)=N²
 * 解法2 : 先对数组归并排序(N * logN) , 然后二分查找(logN) . T(N)=N*logN + logN = N * logN
 *
 * @Author chenpantao
 * @Date 5/15/21 11:53 AM
 * @Version 1.0
 */
public class TwoSumFast {

    public static int count(int[] a) {
        Arrays.sort(a);
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (BinarySearch.rank(-a[i], a) > i) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] ints = In.readInts(args[0]);
        StdOut.println(count(ints));
    }

}
