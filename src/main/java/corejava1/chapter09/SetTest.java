package corejava1.chapter09;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by jiangjiajie on 2017/7/12.
 */
public class SetTest {
    public static void main(String[] args) {
        String[] arr = {"aaa", "bbb", "ccc"};
        Set<String> words = new HashSet<>();
        long totalTime = 0;

        for (String word : arr) {
            long callTime = System.currentTimeMillis();
            words.add(word);
            callTime = System.currentTimeMillis() - callTime;
            totalTime += callTime;
        }

        Iterator<String> iter = words.iterator();
        for (int i = 0; i <= 20 && iter.hasNext(); i++) {
            System.out.println(iter.next());
        }
        System.out.println("...");
        System.out.println(words.size() + " distinct words. " + totalTime + " milliseconds.");
    }
}
