package top.dpdaidai.algorithm.elementary;

/**
 * @Author chenpantao
 * @Date 1/16/21 11:22 AM
 * @Version 1.0
 */
public class Note06_StandardSort implements SortService {

    public static final String NAME = "JDK排序";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void sort(int[] array) {
        ArrayUtil.sort(array);
    }
}
