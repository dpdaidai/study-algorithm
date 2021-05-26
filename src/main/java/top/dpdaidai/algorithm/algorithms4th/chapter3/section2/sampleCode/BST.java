package top.dpdaidai.algorithm.algorithms4th.chapter3.section2.sampleCode;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import top.dpdaidai.algorithm.algorithms4th.chapter1.section3.sampleCode.Queue;

/**
 *
 * 基于二叉查找树的符号表
 * 它将插入和查找操作的增长数量级都降低到 1.39lgN
 *
 * > java BST < tiny.txt
 * min : null
 * size : 10
 * min : A
 * max : X
 * floor K : E
 * ceiling K : L
 * select -1 : null
 * select 0 : A
 * select 9 : X
 * select 10 : null
 * rank A : 0
 * rank B : 1
 * rank E : 1
 * rank X : 9
 * rank Y : 10
 * size(A,X) : 10
 * size(A,Y) : 10
 * size(B,X) : 9
 * size(B,Y) : 9
 * printKey() :
 * A E L M O P R S T X
 * printKey(B,X) :
 * E L M O P R S T X
 * keys() :
 * A 6 E 10 L 9 M 7 O 1 P 8 R 2 S 0 T 3 X 5
 * keys(B,X) :
 * E 10 L 9 M 7 O 1 P 8 R 2 S 0 T 3 X 5
 * deleteMin() :
 * size() : 9
 * keys() :
 * E 10 L 9 M 7 O 1 P 8 R 2 S 0 T 3 X 5
 * deleteMax() :
 * size() : 8
 * keys() :
 * E 10 L 9 M 7 O 1 P 8 R 2 S 0 T 3
 * delete(L) :
 * E 10 M 7 O 1 P 8 R 2 S 0 T 3
 * delete(E) :
 * M 7 O 1 P 8 R 2 S 0 T 3
 * delete(T) :
 * M 7 O 1 P 8 R 2 S 0
 * size() : 5
 * @Author chenpantao
 * @Date 5/25/21 5:48 PM
 * @Version 1.0
 */
public class BST<Key extends Comparable<Key>, Value> {

    private class Node {
        //键
        private Key key;
        //值
        private Value value;
        //左节点
        private Node left;
        //右节点
        private Node right;
        //以该节点为根的树的节点总数
        private int N;

        public Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }

    }

    private Node root;


    //二分查找键
    public Value get(Key key) {
        return get(root, key);

    }

    private Value get(Node x, Key key) {

        //递归结束两个标志
        //1  命中key , 返回value
        //2  未命中key , 指向一个空链接 , 返回null
        if (x == null) {
            return null;
        }
        int compare = key.compareTo(x.key);
        if (compare > 0)
            return get(x.right, key);
        else if (compare < 0)
            return get(x.left, key);
        else
            return x.value;

    }


    public void put(Key key, Value value) {
        //防御性代码
        if (value == null) {
            delete(key);
            return;
        }
        //在递归中重置了引用 , 实际上大部分引用不会发生改变
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        //递归结束两个标志
        //1  命中key , 更新value
        //2  未命中key , 更新空链接
        if (x == null) {
            return new Node(key, value, 1);
        }
        int compare = key.compareTo(x.key);
        if (compare > 0) x.right = put(x.right, key, value);
        else if (compare < 0) x.left = put(x.left, key, value);
        else x.value = value;

        //当compare=0时 , 子树增加了节点 , 那么父节点也需要更新N
        x.N = size(x.left) + size(x.right) + 1;
        return x;

    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.N;
    }

    public Key min() {
        if (isEmpty()) return null;
        return min(root).key;
    }

    private Node min(Node x) {

        if (x.left == null) {
            return x;
        }

        return min(x.left);

    }

    public Key max() {
        if (isEmpty()) return null;
        return max(root).key;

    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else return max(x.right);
    }

    public Key floor(Key key) {
        Node floor = floor(root, key);
        if (floor == null) {
            return null;
        }
        return floor.key;
    }

    private Node floor(Node x, Key key) {

        // 当本节点为nul 时 , 返回null
        if (x == null) return null;

//        1.  key 等于根节点 , 根就是小于等于key的最大键
//        2.  key 小于根节点 , 那么floor(key)一定在根节点得左子树中
//        3.  key 大于根节点
//                + 右子树存在小于等于 key 的节点 , 那么floor(key)在右子树
//                + 否则 根节点就是 小于key的最大键

        int compare = key.compareTo(x.key);
        if (compare == 0) return x;
        if (compare < 0) return floor(x.left, key);

        Node t = floor(x.right, key);
        if (t != null)
            return t;
        else return x;
    }


    public Key ceiling(Key key) {
        Node ceiling = ceiling(root, key);
        if (ceiling == null) {
            return null;
        }
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

    //选择操作
    //假设我们想找到排名为k的键 (从0开始) .
    public Key select(int k) {
        if (root == null) return null;
        if (k < 0 || k >= size(root)) return null;
        return select(root, k).key;
    }

    //1.  左子树中的节点数 t > k , 那么递归的在左子树中查找排名为k的键
    //2.  t = k , 那么根节点就是排名为k的键
    //3.  t < k , 那么递归的在右子树中查找排名为 k-t-1 的键
    private Node select(Node x, int k) {

        if (x == null) return null;

        int size = size(x.left);
        if (k < size) return select(x.left, k);
        if (k > size) return select(x.right, k - size - 1);
//        if (k == size)
        return x;
    }

    //rank() 是select() 的逆方法 , 它会返回给定键的排名
    public int rank(Key key) {
        return rank(root, key);
    }

    //1.  给定键 = 根节点的键 , 返回左子树中的节点总数 t
    //2.  给定键 < 根节点的键 , 返回该键在左子树的排名
    //3.  给定键 > 根节点的键 , 返回该键在右子树的排名 + t + 1
    private int rank(Node x, Key key) {
        if (x == null) return 0;
        int compare = key.compareTo(x.key);
        if (compare < 0) return rank(x.left, key);

        //给定键 > 根节点的键 , 返回该键在右子树的排名 + t + 1
        //t 为左子树节点树 , 1 为根节点自己
        if (compare > 0) return rank(x.right, key) + size(x.left) + 1;
//        if(compare == 0)
        return size(x.left);

    }

    public int size(Key lo, Key hi) {
        int ranklo = rank(lo);
        int rankhi = rank(hi);

        if (rankhi < ranklo) return 0;
        if (contains(hi)) return rankhi - ranklo + 1;
        else return rankhi - ranklo;

    }

    //首先看如果按顺序打印一个树中的所有键
    public void printKey() {
        printKey(min(), max());
    }

    public void printKey(Key lo, Key hi) {
        printKey(root, lo, hi);
    }

    //1.  首先打印根节点的左子树中所有的键
    //2.  打印根节点的键
    //3.  打印右子树中所有的键
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
        keys(root, queue, lo, hi);
        return queue;
    }

    //按顺序返回[lo , hi]所有的键 :
    //1.  lo < 根节点 , 递归的将左子树中所有 大于 lo 的键放入队列
    //2.  lo <= 根节点 <= hi , 将根的键放入队列
    //3.  根节点 < hi , 递归的将右子树所有小于等于 hi 的键放入队列
    private void keys(Node x, Queue queue, Key lo, Key hi) {
        if (x == null) {
            return;
        }

        int comparelo = lo.compareTo(x.key);
        int comparehi = hi.compareTo(x.key);

        if (comparelo < 0) keys(x.left, queue, lo, hi);
        if (comparelo <= 0 && comparehi >= 0) queue.enqueue(x.key);
        if (comparehi > 0) keys(x.right, queue, lo, hi);

    }

    //先删除最小键热身
    //找到最小键 , 然后用它的右子节点替换它的引用即可
    //重置沿途所有的N
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


    //删除一个节点后 , 用它的后继节点替补它的位置 ,
    //那么需要查找右子树的最小键或者左子树的最大键 , 用它们来替换被删除的节点
    //1.  将指向被删除节点的引用保存为 t
    //2.  将右子树最小键的引用保存为 x , 这就是用来替换被删除节点的后继节点
    //3.  删除右子树的最小节点,  然后返回右子树的根节点
    //4.  设置替换节点的左右节点
    //5.  返回替换节点的引用
    //6.  更新沿路所有受影响节点的 N
    public void delete(Key key) {
        if (isEmpty()) return;
        root = delete(root, key);
    }

    //递归 , 每次返回被递归的根节点
    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }

        int compareTo = key.compareTo(x.key);
        //如果x节点比key小 , 那么递归x的左子树
        if (compareTo < 0) x.left = delete(x.left, key);
            //如果x节点比key大 , 那么递归x的右子树
        else if (compareTo > 0) x.right = delete(x.right, key);
        else {

            //判断是不是有两个子节点
            //单子节点的删除比较简单
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;

            //1. 将指向被删除节点的引用保存为 t
            Node t = x;

            //2. 寻找右子树的最小节点来替换被删除的节点
            x = min(t.right);
            //3. 删除右子树的最小节点,  然后返回右子树的根节点
            Node rightRoot = deleteMin(t.right);
            //4. 设置替换节点的右节点
            x.right = rightRoot;
            //5. 设置替换节点的左节点
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }




    public static void main(String[] args) {
        BST<String, Integer> st = new BST<String, Integer>();

        StdOut.println("min : " + st.min());
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }


        StdOut.println("size : " + st.size());

        StdOut.println("min : " + st.min());
        StdOut.println("max : " + st.max());
        StdOut.println("floor K : " + st.floor("K"));
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


//        StdOut.println("check : " + st.check());

    }

}
