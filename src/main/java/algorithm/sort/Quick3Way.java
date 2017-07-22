package algorithm.sort;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by jiangjiajie on 2017/4/11.
 */
public class Quick3Way {

    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        Collections.shuffle(Arrays.asList(a));
        sort(a, 0, a.length);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi)
            return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i < gt) {
            int comp = a[i].compareTo(v);
            if (comp > 0)
                exch(a, i, --gt);
            else if (comp < 0)
                exch(a, lt++, i++);
            else
                i++;
        }
        sort(a, lo, lt);
        sort(a, gt, hi);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i-1]))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] s = {"aa", "bb", "cc","ee","dd", "ff", "11", "22", "33", "00"};
        sort(s);
        show(s);
    }
}
