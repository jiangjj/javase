package algorithm.sort;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by jiangjiajie on 2017/4/11.
 */
public class Quick {

    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        Collections.shuffle(Arrays.asList(a));
        sort(a, 0, a.length);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi)
            return;
        int k = partition(a, lo, hi);
        sort(a, lo, k - 1);
        sort(a, k + 1, hi);
    }

    public static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi;
        Comparable v = a[i];
        while (true) {
            while (j > lo && less(v, a[--j]))
                if (j == lo)
                    break;
            while (i < hi - 1 && less(a[++i], v))
                if (i == hi)
                    break;
            if (i >= j)
                break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
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
