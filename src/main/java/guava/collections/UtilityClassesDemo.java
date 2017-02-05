package guava.collections;

import com.google.common.collect.*;
import com.google.common.primitives.Ints;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jiangjiajie on 2017/2/3.
 */
public class UtilityClassesDemo {
    public static void main(String[] args) {
        List<String> list = Lists.newArrayList();
        Map<String, String> map = Maps.newLinkedHashMap();
        Iterable<Integer> concatenated = Iterables.concat(Ints.asList(1, 2, 3),
                Ints.asList(4, 5, 6));
        Iterables.getLast(concatenated);
        Iterables.getOnlyElement(concatenated);
        List<Integer> countUp = Ints.asList(1, 2, 3, 4, 5);
        List<Integer> countDown = Lists.reverse(countUp); // {5, 4, 3, 2, 1}
        List<List<Integer>> parts = Lists.partition(countUp, 2); // {{1, 2}, {3, 4}, {5}}
        Set<String> wordsWithPrimeLength = ImmutableSet.of("one", "two", "three", "six", "seven", "eight");
        Set<String> primes = ImmutableSet.of("two", "three", "five", "seven");

        Sets.SetView<String> intersection = Sets.intersection(primes, wordsWithPrimeLength); // contains "two", "three", "seven"
// I can use intersection as a Set directly, but copying it can be more efficient if I use it a lot.
        intersection.immutableCopy();
    }
}
