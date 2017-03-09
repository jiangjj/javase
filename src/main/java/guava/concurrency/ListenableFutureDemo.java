package guava.concurrency;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * Created by jiangjiajie on 2017/2/14.
 */
public class ListenableFutureDemo {
    public static void main(String[] args) {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<Integer> task = service.submit(() -> 1);
        Futures.addCallback(task, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                System.out.println("success " + result);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
