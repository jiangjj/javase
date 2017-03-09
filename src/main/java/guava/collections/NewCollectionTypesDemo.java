package guava.collections;

import com.google.common.collect.*;

import java.util.Set;

/**
 * Created by jiangjiajie on 2017/2/2.
 */
public class NewCollectionTypesDemo {
    public static void main(String[] args) {
        Multimap<String, String> multimap = ArrayListMultimap.create();
        HashBiMap<String, Integer> bitMap = HashBiMap.create();
        bitMap.inverse().get(1);
        Table<String, String, Integer> table = HashBasedTable.create();
        table.row("rowKey");
        table.column("columnKey");
        ClassToInstanceMap<Number> classToInstanceMap = MutableClassToInstanceMap.create();
        classToInstanceMap.putInstance(Integer.class, Integer.valueOf(0));
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1,10)); // {[1, 10]}
        rangeSet.remove(Range.open(5,10)); // splits [1, 10];{[1,5],[10,10]
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1, 10), "foo");
        rangeMap.put(Range.open(3, 6), "bar");
        rangeMap.remove(Range.closed(5,10));
    }
}
