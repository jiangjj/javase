package algorithm.sort;

/**
 * Created by jiangjiajie on 2017/4/11.
 */
public class MaxPQ<Key extends Comparable<Key>>{

    private Key[] pq;
    private int N = 0;

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, N--);
        sink(1);
        return max;
    }
    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k/2, k);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (k * 2 <= N) {
            int j = k * 2;
            if (j < N && less(j, j + 1))
                j++;
            if (!less(k, j))
                break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int v, int w) {
        return pq[v].compareTo(pq[w]) < 0;
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MaxPQ<String> maxPQ = new MaxPQ<>(10);
        maxPQ.insert("cc");
        maxPQ.insert("aa");
        maxPQ.insert("dd");
        maxPQ.insert("00");
        System.out.println(maxPQ.delMax());
        System.out.println(maxPQ.delMax());
        System.out.println(maxPQ.delMax());
        System.out.println(maxPQ.delMax());
    }
}
