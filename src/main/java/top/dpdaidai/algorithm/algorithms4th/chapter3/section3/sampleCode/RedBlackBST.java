package top.dpdaidai.algorithm.algorithms4th.chapter3.section3.sampleCode;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import top.dpdaidai.algorithm.algorithms4th.chapter1.section3.sampleCode.Queue;

/**
 *
 * 红黑树
 *
 * @Author chenpantao
 * @Date 5/27/21 4:41 PM
 * @Version 1.0
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {

    public static final boolean RED = true;
    public static final boolean BLACK = false;

    private class Node {
        private Key key;
        private Value value;
        //颜色 , 默认 false = 黑色 . true = 红色
        private boolean color;
        private Node left;
        private Node right;
        private int N;

        public Node(Key key, Value value, boolean color, int N) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.N = N;
        }

    }

    //判断节点是否是红色
    private boolean isRed(Node x) {
        if (x == null) return BLACK;
        return x.color == RED;
    }

    //将x节点左旋 , 然后返回新的根节点
    //交换 x 和右子节点的父子关系
    //1  将右子节点的左子节点交给x
    //2  将右子节点的左子节点设置为x
    //3  交换两个节点颜色
    //4  修改两个节点的N
    //5  返回新的根节点
    private Node rotateLeft(Node x) {
        Node right = x.right;
        //1
        x.right = right.left;
        //2
        right.left = x;
        //3
        right.color = x.color;
        x.color = RED;
        //4
        right.N = x.N;
        x.N = size(x.left) + size(x.right) + 1;
        //5
        return right;

    }

    //将x节点右旋 , 然后返回新节点
    //交换 x 和左子节点的父子关系
    //1  将左子节点的右子节点交给x
    //2  将左子节点的右子节点设置为x
    //3  交换两个节点颜色
    //4  修改两个节点的N
    //5  返回新的根节点
    private Node rotateRight(Node x) {
        Node left = x.left;
        //1
        x.left = left.right;
        //2
        left.right = x;
        //3
        left.color = x.color;
        x.color = RED;
        //4
        left.N = x.N;
        x.N = size(x.left) + size(x.right) + 1;
        //5
        return left;
    }

    //通过转换链接颜色来分解 4- 节点
    private void filpColors(Node x) {
        x.left.color = BLACK;
        x.right.color = BLACK;
        x.color = RED;
    }

    private Node root;

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return size(x.left) + size(x.right) + 1;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    //二叉树插入
    //1  查找命中则更新
    //2  查找未命中则插入
    //3  如果 key 小于 x.key , 那么在 左子树 中寻找 , 返回值为左子树的根节点
    //4  如果 key 大于 x.key , 那么在 右子树 中寻找 , 返回值为右子树的根节点
    //5  如果 key 等于 x.key , 更新value , 然后返回 x
    //6  如果不是情况5 , 那么属于未命中 , 需要更新 沿途节点的 N 和 恢复树的平衡

    //红黑树操作 , 保持整棵树的平衡性 , 从被插入节点开始 , 自底向上 , 对每一个经过的节点进行恢复
    //7  如果 当前节点的右链接为红色 , 左旋
    //8  如果 当前节点的左链接为红色 , 左子节点的左链接还是红色 , 右旋
    //9  如果 当前节点的 左链接 和 右链接 都是红色 , 那么颜色变换 , 将 4- 节点分解 , 中间节点 送入父节点
    private Node put(Node x, Key key, Value value) {

        //如果查找未命中 , 返回一个新的红色子节点
        if (x == null) return new Node(key, value, RED, 1);

        int compare = key.compareTo(x.key);
        //3
        if (compare < 0) x.left = put(x.left, key, value);
            //4
        else if (compare > 0) x.right = put(x.right, key, value);
            //5
        else if (compare == 0) {
            x.value = value;
            return x;
        }

        //6
        x.N = size(x);

        //7
        if (!isRed(x.left) && isRed(x.right)) x = rotateLeft(x);
        //8  --> 这里如何避免空指针异常
        //因为空节点是黑色的 , 如果左子节点为红色 , 那么它一定不是空 , 所以不会异常
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        //9
        if (isRed(x.left) && isRed(x.right)) filpColors(x);

        return x;

    }


    public Value get(Key key) {
        Node node = get(root, key);
        if (node == null) return null;
        return node.value;
    }

    //1 如果 key 小于 x.key , 那么在 左子树 中寻找
    //2 如果 key 大于 x.key , 那么在 右子树 中寻找
    //3 如果 key 等于 x.key , 返回node
    //4 如果 子树 为空 , 那么就是未命中
    private Node get(Node x, Key key) {
        if (x == null) return null;
        int compare = key.compareTo(x.key);
        if (compare < 0) return get(x.left, key);
        if (compare > 0) return get(x.right, key);
        return x;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Key min() {
        if (root == null) return null;
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left != null) return min(x.left);
        return x;
    }

    public Key max() {
        if (root == null) return null;
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right != null) return max(x.right);
        return x;
    }


    //查找小于等于 key 的最大键
    public Key floor(Key key) {
        Node floor = floor(root, key);
        if (floor == null) return null;
        return floor.key;
    }

    //1  如果 key = x.key , 直接返回x
    //2  如果 key < x.key , 那么floor(key) 一定在 x 的左子树中
    //3  如果 key > x.key
    //3.1  key小于x的右子树中所有的键  , 那么 x 就是 小于等于 key 的最大值
    //3.2  x的右子树中有小于等于 key 的键 , 重复 3
    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int compare = key.compareTo(x.key);

        //1
        if (compare == 0) return x;
            //2
        else if (compare < 0) return floor(x.left, key);
            //3
        else {
            Node floor = floor(x.right, key);
            if (floor != null) return floor;
            return x;
        }

    }

    //查找大于等于key 的最小键
    public Key ceiling(Key key) {

        Node ceiling = ceiling(root, key);
        if (ceiling == null) return null;
        return ceiling.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;

        int compare = key.compareTo(x.key);

        if (compare == 0) return x;
        if (compare > 0) return ceiling(x.right, key);

        Node ceiling = ceiling(x.left, key);
        if (ceiling != null) return ceiling;
        return x;
    }

    //查询排名为k的键 , 起始为0
    public Key select(int k) {
        if (isEmpty()) return null;
        if (k < 0 || k >= root.N) return null;
        return select(root, k).key;
    }

    // 如果左子节点的数量为 t
    //1  t > k , 那么 key 在左子树中
    //2  t = k , 那么 根节点 就是目标key
    //3  t < k , 那么 右子树中 排名为 k - t -1 的键就是目标元素
    private Node select(Node x, int k) {
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        if (t < k) return select(x.right, k - t - 1);
        return x;
    }

    //查询指定key在树中的排名
    public int rank(Key key) {
        return rank(root, key);
    }

    //1  指定键 = 根节点 , 返回左子树的键数
    //2  指定键 < 根节点 , 返回指定键在左子树的排名
    //3  指定键 > 根节点 , 返回指定键在右子树的排名 + 左子树的数量 + 1
    private int rank(Node x, Key key) {
        if (x == null) return 0;
        int compareTo = key.compareTo(x.key);
        if (compareTo < 0) return rank(x.left, key);
        if (compareTo > 0) return rank(x.right, key) + size(x.left) + 1;
        return size(x.left);
    }


    //查询指定键内数量
    //利用两个键的排名来查询中间的数量
    public int size(Key lo, Key hi) {

        int ranklo = rank(lo);
        int rankhi = rank(hi);

        if (rankhi < ranklo) return 0;
        if (contains(hi)) return rankhi - ranklo + 1;
        else return rankhi - ranklo;
    }

    public void printKey() {
        printKey(min(), max());
    }

    public void printKey(Key lo, Key hi) {
        if (isEmpty()) return;
        printKey(root, lo, hi);
    }

    //1 lo <= x.key <= hi
    //2 打印左子树
    //3 打印根节点
    //4 打印右子树
    private void printKey(Node x, Key lo, Key hi) {
        if (x == null) return;
        int comparelo = lo.compareTo(x.key);
        int comparehi = hi.compareTo(x.key);
        if (comparelo < 0) printKey(x.left, lo, hi);
        if (comparelo <= 0 && comparehi >= 0) StdOut.print(x.key + " ");
        if (comparehi > 0) printKey(x.right, lo, hi);
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        if (isEmpty()) return queue;
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue queue, Key lo, Key hi) {
        if (x == null) return;
        int comparelo = lo.compareTo(x.key);
        int comparehi = hi.compareTo(x.key);
        if (comparelo < 0) keys(x.left, queue, lo, hi);
        if (comparelo <= 0 && comparehi >= 0) queue.enqueue(x.key);
        if (comparehi > 0) keys(x.right, queue, lo, hi);
    }

    public void deleteMin() {
        if (isEmpty()) return;
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        else x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax() {
        if (isEmpty()) return;
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        else x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;

    }

    public void delete(Key key) {
        if (isEmpty()) return;
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int compareTo = key.compareTo(x.key);
        if (compareTo < 0) x.left = delete(x.left, key);
        else if (compareTo > 0) x.right = delete(x.right, key);
        else {

            if (x.left == null) return x.right;
            if (x.right == null) return x.left;

            Node min = min(x.right);

            min.right = deleteMin(x.right);
            min.left = x.left;

            x = min;

        }

        x.N = size(x.left) + size(x.right) + 1;
        return x;

    }

    public static void main(String[] args) {

        RedBlackBST<String, Integer> st = new RedBlackBST<String, Integer>();

        StdOut.println("size : " + st.size());
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        StdOut.println("size : " + st.size());
        StdOut.println("get(A) : " + st.get("A"));
        StdOut.println("get(B) : " + st.get("B"));
        StdOut.println("get(X) : " + st.get("X"));
        StdOut.println("get(Y) : " + st.get("Y"));
        StdOut.println("min : " + st.min());
        StdOut.println("max : " + st.max());
        StdOut.println("floor K : " + st.floor("K"));
        StdOut.println("floor A : " + st.floor("A"));
        StdOut.println("floor Z : " + st.floor("Z"));
        StdOut.println("ceiling K : " + st.ceiling("K"));
        StdOut.println("select -1 : " + st.select(-1));
        StdOut.println("select 0 : " + st.select(0));
        StdOut.println("select 9 : " + st.select(9));
        StdOut.println("select 10 : " + st.select(10));
        StdOut.println("rank A : " + st.rank("A"));
        StdOut.println("rank B : " + st.rank("B"));
        StdOut.println("rank E : " + st.rank("E"));
        StdOut.println("rank X : " + st.rank("X"));
        StdOut.println("rank Y : " + st.rank("Y"));
        StdOut.println("size(A,X) : " + st.size("A", "X"));
        StdOut.println("size(A,Y) : " + st.size("A", "Y"));
        StdOut.println("size(B,X) : " + st.size("B", "X"));
        StdOut.println("size(B,Y) : " + st.size("B", "Y"));

        StdOut.println("printKey() : ");
        st.printKey();
        StdOut.println();
        StdOut.println("printKey(B,X) : ");
        st.printKey("B", "X");

        StdOut.println();
        StdOut.println("keys() : ");
        for (String s : st.keys())
            StdOut.print(s + " " + st.get(s) + " ");
        StdOut.println();

        StdOut.println("keys(B,X) : ");
        for (String s : st.keys("B", "X"))
            StdOut.print(s + " " + st.get(s) + " ");
        StdOut.println();

        StdOut.println("deleteMin() : ");
        st.deleteMin();
        StdOut.println("size() : " + st.size());


        StdOut.println("keys() : ");
        for (String s : st.keys())
            StdOut.print(s + " " + st.get(s) + " ");
        StdOut.println();

        StdOut.println("keys() : ");
        for (String s : st.keys())
            StdOut.print(s + " " + st.get(s) + " ");
        StdOut.println();

        StdOut.println("deleteMax() : ");
        st.deleteMax();
        StdOut.println("size() : " + st.size());


        StdOut.println("keys() : ");
        for (String s : st.keys())
            StdOut.print(s + " " + st.get(s) + " ");
        StdOut.println();

        StdOut.println("delete(L) : ");
        st.delete("L");
        for (String s : st.keys())
            StdOut.print(s + " " + st.get(s) + " ");
        StdOut.println();

        StdOut.println("delete(E) : ");
        st.delete("E");
        for (String s : st.keys())
            StdOut.print(s + " " + st.get(s) + " ");
        StdOut.println();


        StdOut.println("delete(T) : ");
        st.delete("T");
        for (String s : st.keys())
            StdOut.print(s + " " + st.get(s) + " ");
        StdOut.println();

        StdOut.println("size() : " + st.size());


    }


}
