package guava.caches;

import com.google.common.cache.*;
import com.google.common.graph.Graph;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by jiangjiajie on 2017/2/14.
 */
public class CacheDemo {
    public static void main(String[] args) {
        LoadingCache<String, Integer> cacheWithException = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(
                        new CacheLoader<String, Integer>() {
                            @Override
                            public Integer load(String key) throws Exception {
                                return key.length();
                            }
                        }
                );

        try {
            System.out.println(cacheWithException.get("ab"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        LoadingCache<String, Integer> cacheWithoutException = CacheBuilder.newBuilder()
//                .maximumSize(1000)
                .maximumWeight(30000)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .weigher((Weigher<String, Integer>) (key, value) -> key.length())
                .removalListener(new RemovalListener<String, Integer>() {
                    @Override
                    public void onRemoval(RemovalNotification<String, Integer> notification) {
                        System.out.println("removed" + notification);
                    }
                })
                .recordStats()
                .build(
                        new CacheLoader<String, Integer>() {
                            @Override
                            public Integer load(String key) {
                                return key.length();
                            }

                            @Override
                            public ListenableFuture<Integer> reload(String key, Integer oldValue) throws Exception {
                                return super.reload(key, oldValue);
                            }
                        }
                );
        System.out.println(cacheWithoutException.getUnchecked("abc"));
        try {
            System.out.println(cacheWithoutException.get("a", new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return 10;
                }
            }));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        cacheWithoutException.put("b", 5);
        try {
            System.out.println(cacheWithoutException.get("b"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        cacheWithoutException.invalidate("b");
        try {
            System.out.println(cacheWithoutException.get("b"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        cacheWithoutException.refresh("b");
        final CacheStats stats = cacheWithoutException.stats();
        System.out.println(stats.requestCount());
    }
}
