package concurrency;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by jiangjiajie on 2017/3/21.
 */
public class ExecutorDemo {

    public static void main(String[] args) throws Exception{
        final int size = 10;
        final CountDownLatch latch = new CountDownLatch(size);
        Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        for (int i = 0; i < size; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());

                    latch.countDown();
                }
            });
        }
        latch.await();
        System.out.println(Thread.currentThread().getName());
    }
}
