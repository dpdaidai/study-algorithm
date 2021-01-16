package top.dpdaidai.algorithm.elementary;

/**
 * 递归函数体现的是分治的思想
 * 递归调用自己时, 会将当前状态压入栈中保存, 等待子函数结果返回时, 再从栈中弹出
 * <p>
 * 本质上还是 由一次次的压栈和出栈构成函数调用的过程
 * <p>
 * 本例子使用递归找数组中的最大值
 * 本例中的
 *
 * @Author chenpantao
 * @Date 1/15/21 10:44 PM
 * @Version 1.0
 */
public class Note07_RecursiveDemo {

    public static final int testTime = 2000;
    public static final int maxSize = 10000;
    public static final int maxValue = 100000;

    public static void main(String[] args) {
        long recursiveTimeConsuming = 0;
        long cycleTimeConsuming = 0;

        for (int i = 0; i < Note07_RecursiveDemo.testTime; i++) {

            int[] array = ArrayUtil.generateSpecifyLengthRandomArray(Note07_RecursiveDemo.maxSize, Note07_RecursiveDemo.maxValue);

            long start = System.currentTimeMillis();
            int max = getMax(array, 0, array.length - 1);

            long middle = System.currentTimeMillis();

            int max1 = ArrayUtil.getMax(array);
            long end = System.currentTimeMillis();

            recursiveTimeConsuming += (middle - start);
            cycleTimeConsuming += (end - middle);

            if (max != max1) {
                System.out.println("方法有误 : max = " + max + " , max1 = " + max1);
                ArrayUtil.printArray(array);
                break;
            }
        }

        System.out.println(Note07_RecursiveDemo.testTime + " 次 , 递归耗时 : " + recursiveTimeConsuming);
        System.out.println("递归平均耗时 : " + recursiveTimeConsuming / Note07_RecursiveDemo.testTime);

        System.out.println(Note07_RecursiveDemo.testTime + " 次 , 遍历耗时 : " + cycleTimeConsuming);
        System.out.println("遍历平均耗时 : " + cycleTimeConsuming / Note07_RecursiveDemo.testTime);

//        2000 次 , 递归耗时 : 79
//        递归平均耗时 : 0
//        2000 次 , 遍历耗时 : 8
//        遍历平均耗时 : 0

    }

    public static int getMax(int[] array, int left, int right) {

        if (left == right) {
            return array[left];
        }

        int mid = (left + right) / 2;

        int leftMax = getMax(array, left, mid);
        int rightMax = getMax(array, mid + 1, right);
        return Math.max(leftMax, rightMax);

    }


}
