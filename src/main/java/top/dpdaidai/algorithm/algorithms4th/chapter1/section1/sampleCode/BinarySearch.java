package top.dpdaidai.algorithm.algorithms4th.chapter1.section1.sampleCode;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * 二分查找作者提供了while循环实现和递归实现两种方式
 *
 * @Author chenpantao
 * @Date 5/10/21 12:00 PM
 * @Version 1.0
 */
public class BinarySearch {

    /**
     * 递归实现查找key在升序数组array中的index
     */
    public static int rankRecursion(int key, int[] array) {
        return rankRecursion(key, array, 0, array.length - 1);
    }

    public static int rankRecursion(int key, int[] array, int start, int end) {
        //如果key在数组中 , 它的索引end不会小于start
        if (end < start) return -1;
        //求当前区间中间数index
        int middle = start + (end - start) / 2;
        if (key < array[middle]) return rankRecursion(key, array, start, middle - 1);
        else if (key > array[middle]) return rankRecursion(key, array, middle + 1, end);
        else return middle;
    }

    /**
     * while实现查找key在升序数组array中的index
     */
    public static int rankWhile(int key, int[] array) {
        int start = 0;
        int end = array.length - 1;
        while (start <= end) {
            int middle = start + (end - start) / 2;
            if (key < array[middle]) end = middle - 1;
            else if (key > array[middle]) start = middle + 1;
            else return middle;
        }
        return -1;
    }

    public static void main(String[] args) {

        // java top.dpdaidai.algorithm.algorithms4th.chapter1.section1.sampleCode.BinarySearch tinyW.txt < tinyT.txt
        // 一次读取整个args[0]的数据到whiteList中
        int[] whiteList = In.readInts(args[0]);
        Arrays.sort(whiteList);

        int count = 0;

        // tinyT.txt的数据则是由 StdIn.readInt() 输入流式挨个读取
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (rankRecursion(key, whiteList) < 0) {
                StdOut.println(key);
                count++;
            }
        }

        StdOut.printf("Count : " + count);

    }

}
