package corejava1.chapter14;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Created by jiangjiajie on 2017/7/15.
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Enter base directory:");
            String directory = in.nextLine();
            System.out.println("Enter keyword:");
            String keyword = in.nextLine();

            ExecutorService pool = Executors.newCachedThreadPool();
        }
    }
}
 class MatchCounter1 implements Callable<Integer> {

    private File directory;
    private String keyword;
    private ExecutorService pool;
    private int count;

     public MatchCounter1(File directory, String keyword, ExecutorService pool) {
         this.directory = directory;
         this.keyword = keyword;
         this.pool = pool;
     }

     @Override
     public Integer call() throws Exception {
         count = 0;
         try {
             File[] files = directory.listFiles();
             List<Future<Integer>> results = new ArrayList<>();

             for (File file : files) {
                 if (file.isDirectory()) {
                     MatchCounter1 counter = new MatchCounter1(file, keyword, pool);
                     Future<Integer> result = pool.submit(counter);
                     results.add(result);
                 } else {
                     if (search(file))
                         count++;
                 }
             }

             for (Future<Integer> result : results) {
                 try {
                     count += result.get();
                 }catch (ExecutionException e) {
                     e.printStackTrace();
                 }
             }
         } catch (InterruptedException e){}
         return count;
     }

     public boolean search(File file) {
         try (Scanner in = new Scanner(file, "UTF-8")) {
             boolean found = false;
             while (!found && in.hasNextLine()) {
                 String line = in.nextLine();
                 if (line.contains(keyword))
                     found = true;
             }
             return found;
         } catch (IOException e) {
             return false;
         }
     }
 }
