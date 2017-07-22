package corejava1.chapter09;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by jiangjiajie on 2017/7/12.
 */
public class TreeSetTest {
    public static void main(String[] args) {
        SortedSet<Item> parts = new TreeSet<>();
        parts.add(new Item("Toaster", 1234));
        parts.add(new Item("Widget", 4566));
        parts.add(new Item("Modem", 9912));
        System.out.println(parts);

        NavigableSet<Item> sortByDescription = new TreeSet<>(
                Comparator.comparing(Item::getDescription)
        );

        sortByDescription.addAll(parts);
        System.out.println(sortByDescription);
    }
}
