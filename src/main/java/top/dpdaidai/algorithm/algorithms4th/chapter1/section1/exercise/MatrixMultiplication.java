package top.dpdaidai.algorithm.algorithms4th.chapter1.section1.exercise;

/**
 * 矩阵相乘
 * 矩阵c = a * b
 * 它只有在第一个矩阵的列数（column）和第二个矩阵的行数（row）相同时才有意义 [1]  。
 *
 * https://baike.baidu.com/item/%E7%9F%A9%E9%98%B5%E4%B9%98%E6%B3%95
 *
 * 实际应用 :
 *  1  数据统计
 *      某公司有四个工厂，分布在不同地区，同时三种产品，产量（单位；t），试用矩阵统计这些数据。
 *  2  路径问题
 *      给定一个有向图，问从A点恰好走k步（允许重复经过边）到达B点的方案数。
 *
 *
 * @Author chenpantao
 * @Date 4/25/21 12:09 PM
 * @Version 1.0
 */
public class MatrixMultiplication {

    public static void main(String[] args) {
        int[][] a = new int[2][3];
        int[][] b = new int[3][2];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = i + j;
                b[j][i] = i + j;
            }
        }

        printTwoDimensionalArray(a);
        printTwoDimensionalArray(b);

//        StdOut.println("d");
//        () -> BinaryIn

        int length = a.length;
        int[][] c = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                for (int k = 0; k < a[i].length; k++) {
                    c[i][j] += (a[i][k] * b[k][j]);
                }
            }
        }

        printTwoDimensionalArray(c);
    }

    private static void printTwoDimensionalArray(int[][] array){
        System.out.println("print array");
        for (int i = 0; i < array.length; i++) {
            String s = "";
            for (int j = 0; j < array[i].length; j++) {
                s += array[i][j] + "  ";
            }
            System.out.println(s);
        }

    }
}
