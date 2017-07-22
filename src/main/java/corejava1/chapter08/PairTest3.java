package corejava1.chapter08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jiangjiajie on 2017/7/15.
 */
public class PairTest3 {
    public static void main(String[] args) {
        Manager ceo = new Manager("ceo");
        Manager cfo = new Manager("cfo");

        List<Manager> list = Arrays.asList(ceo, cfo);
        printBuddies(list);

        Manager[] managers = {ceo, cfo};

        List<Manager> result = new ArrayList<>(2);
        minmax(managers, result);
        System.out.println("first: " + result.get(0).getName() + ", second: " + result.get(1).getName());
        maxmin(managers, result);
        System.out.println("first: " + result.get(0).getName() + ", second: " + result.get(1).getName());
    }

    public static void printBuddies(List<? extends Employee> p) {
        Employee first = p.get(0);
        Employee second = p.get(1);
        System.out.println(first.getName() + " and " + second.getName() + " are buddies.");
    }

    public static void minmax(Manager[] a, List<? super Manager> result) {
        if (a.length == 0) return;
        Manager min = a[0];
        Manager max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (min.getName().compareTo(a[i].getName()) > 0) min = a[i];
            if (max.getName().compareTo(a[i].getName()) < 0) max = a[i];
        }
        result.clear();
        result.add(min);
        result.add(max);
    }

    public static void maxmin(Manager[] a, List<? super Manager> result) {
        minmax(a, result);
        PairAlg.swapHelper(result);
    }
}

class PairAlg {
    public static boolean hasNulls(List<?> p) {
        return p.get(0) == null || p.get(1) == null;
    }

    public static void swap(List<?> p) {
        swapHelper(p);
    }

    public static <T> void swapHelper(List<T> p) {
        T t = p.get(0);
        p.set(0, p.get(1));
        p.set(1, t);
    }
}


