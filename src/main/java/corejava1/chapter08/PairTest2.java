package corejava1.chapter08;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jiangjiajie on 2017/7/15.
 */
public class PairTest2 {
    public static void main(String[] args) {
        LocalDate[] birthdays = {
                LocalDate.of(1906, 12, 9),
                LocalDate.of(1815, 12, 10),
                LocalDate.of(1903, 12, 3),
                LocalDate.of(1910, 6, 22)
        };
        List<LocalDate> list = ArrayAlg1.minmax(birthdays);
        System.out.println("min=" + list.get(0));
        System.out.println("max=" + list.get(1));
    }
}

class ArrayAlg1 {
    public static <T extends Comparable>List<T> minmax(T[] a) {
        if (a == null || a.length == 0)
            return null;
        T min = a[0];
        T max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (max.compareTo(a[i]) < 0) max = a[i];
        }
        return Arrays.asList(min, max);
    }
}
