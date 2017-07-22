package corejava1.chapter08;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jiangjiajie on 2017/7/15.
 */
public class PairTest1 {
    public static void main(String[] args) {
        String[] words = {"Mary", "had", "a", "little", "lamb"};
        List<String> list = ArrayAlg.minmax(words);
        System.out.println("min=" + list.get(0));
        System.out.println("max=" + list.get(1));
    }
}

class ArrayAlg {
    public static List<String> minmax(String[] a) {
        if (a == null || a.length == 0)
            return null;
        String min = a[0];
        String max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (max.compareTo(a[i]) < 0) max = a[i];
        }
        return Arrays.asList(min, max);
    }
}
