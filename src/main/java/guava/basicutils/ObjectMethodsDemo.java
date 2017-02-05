package guava.basicutils;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

/**
 * Created by jiangjiajie on 2017/2/1.
 */
public class ObjectMethodsDemo implements Comparable<ObjectMethodsDemo>{
    public static void main(String[] args) {
        Objects.equal("a", "a"); // returns true
        Objects.equal(null, "a"); // returns false
        Objects.equal("a", null); // returns false
        Objects.equal(null, null); // returns true
        Objects.hashCode("a");

    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("x", 1).toString();// returns "ClassName{x=1}
    }

    private String s;
    private int i;
    public int compareTo(ObjectMethodsDemo o) {
        return ComparisonChain.start().compare(this.s, o.s).compare(this.i, o.i).result();
    }
}
