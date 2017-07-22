package algorithm.sort;

/**
 * Created by jiangjiajie on 2017/4/11.
 */
public class MergeBU {

    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        for (int sz = 1; sz < a.length; sz += sz) {
            for (int lo = 0; lo < a.length - sz; lo += sz + sz) {
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz, a.length));
            }
        }
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        for (int i = lo; i < hi; i++) {
            aux[i] = a[i];
        }
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k < hi; k++) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j >= hi)
                a[k] = aux[i++];
            else {
                if (less(aux[i], aux[j])) {
                    a[k] = aux[i++];
                } else {
                    a[k] = aux[j++];
                }
            }
        }
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
        String[] s = {"bb", "cc", "aa","ab"};
        sort(s);
        show(s);
    }
}
