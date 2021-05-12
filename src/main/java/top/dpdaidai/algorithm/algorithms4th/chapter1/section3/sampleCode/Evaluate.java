package top.dpdaidai.algorithm.algorithms4th.chapter1.section3.sampleCode;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * 将一个字符串解释为一段程序 , 并执行该程序得到结果
 * 这段代码假设
 *      1   表达式没有省略任何括号
 *      2   数字和字符均匀空白字符相隔
 *      3   支持常见二元运算符 + - * / 和只接受一个参数的平方根运算符sqrt
 *
 * https://github.com/aistrate/AlgorithmsSedgewick/blob/master/1-Fundamentals/1-3-BagsQueuesStacks/Evaluate.java
 * ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )
 * ( ( 1 + sqrt ( 4 ) ) / 2.0 )
 * ( 1 ( ( 2 3 + ) ( 4 5 * ) * ) + )
 * 1 2 3 + 4 5 * * +
 *
 *
 * @Author chenpantao
 * @Date 5/12/21 5:33 PM
 * @Version 1.0
 */
public class Evaluate {

    public static void main(String[] args) {

        //保存运算符
        Stack<String> ops = new Stack<>();
        //保存运算数据
        Stack<Double> vals = new Stack<>();

        String[] ss = args[0].split("\\s+");
        for (String s : ss) {
            System.out.println(s);
        }

        for (String s : ss) {
            //做括号忽略
            if      (s.equals("("))               ;
                //如果是运算符压入栈
            else if (s.equals("+"))    ops.push(s);
            else if (s.equals("-"))    ops.push(s);
            else if (s.equals("*"))    ops.push(s);
            else if (s.equals("/"))    ops.push(s);
            else if (s.equals("sqrt")) ops.push(s);
                //如果是右括号 , 弹出运算符和操作数, 计算结果并压入栈
            else if (s.equals(")")) {
                String op = ops.pop();
                Double v = vals.pop();
                if      (op.equals("+"))    v = vals.pop() + v;
                else if (op.equals("-"))    v = vals.pop() - v;
                else if (op.equals("*"))    v = vals.pop() * v;
                else if (op.equals("/"))    v = vals.pop() / v;
                else if (op.equals("sqrt")) v = Math.sqrt(v);
                vals.push(v);
            }
            //如果既不是括号 , 也不是运算符, 将它作为double值压入栈
            else vals.push(Double.parseDouble(s));
        }

        StdOut.println(vals.pop());


    }

}
