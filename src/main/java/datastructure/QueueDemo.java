package datastructure;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by jiangjiajie on 2017/4/6.
 */
public class QueueDemo {
    public static void main(String[] args) {
        Queue<String> queue = new ArrayBlockingQueue<>(10);
        queue.add("a");
        queue.add("b");
        System.out.println(queue.peek());
        System.out.println(queue.remove());
        System.out.println(queue.poll());
        System.out.println(queue.isEmpty());

    }
}
