package guava.basicutils;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import com.sun.istack.internal.Nullable;

/**
 * Created by jiangjiajie on 2017/2/1.
 */
public class OrderingDemo {
    public static void main(String[] args) {
        Ordering<String> byLengthOrdering = new Ordering<String>() {
            @Override
            public int compare(String left, String right) {
                return Ints.compare(left.length(), right.length());
            }
        };
        byLengthOrdering.reverse();
        byLengthOrdering.nullsFirst();
        byLengthOrdering.lexicographical();
        Ordering<Foo> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<Foo, String>() {
            public String apply(Foo input) {
                return input.sortedBy;
            }
        });
        
    }

    private class Foo {
        @Nullable String sortedBy;
        int notSortedBy;
    }
}
