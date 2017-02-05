package guava.basicutils;

import com.google.common.base.Optional;

/**
 * Created by jiangjiajie on 2017/2/1.
 */
public class OptionalDemo {
    public static void main(String[] args) {
        Optional<Integer> possible = Optional.of(5);
        possible.isPresent();// returns true
        possible.get();// returns 5
    }
}
