package algorithm.sort;

/**
 * Created by jiangjiajie on 2017/4/11.
 */
public class Heap{

    public static void sort(Comparable[] a) {
        int N = a.length - 1;
        for (int k = N / 2; k >= 1; k--)
            sink(a, k, N);
        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }

    private static void sink(Comparable[] a, int k, int N) {
        while (k * 2 <= N) {
            int j = k * 2;
            if (j < N && less(a[j], a[j+1]))
                j++;
            if (!less(a[k], a[j]))
                break;
            exch(a, k, j);
            k = j;
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

    public static void main(String[] args) {
        String[] s = {"", "aa", "bb", "cc","ee","dd", "ff", "11", "22", "33", "00"};
        sort(s);
        show(s);
    }
}
