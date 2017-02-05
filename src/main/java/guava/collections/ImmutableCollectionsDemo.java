package guava.collections;

import com.google.common.collect.ImmutableSet;

/**
 * Created by jiangjiajie on 2017/2/2.
 */
public class ImmutableCollectionsDemo {
    public static void main(String[] args) {
        //ImmutableSet.copyOf(set);
        ImmutableSet.of("a", "b");
        ImmutableSet<String> immutableSet = ImmutableSet.<String>builder().add("a").build();
        immutableSet.asList().get(0);
    }
}
