package corejava1.chapter14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by jiangjiajie on 2017/7/15.
 */
public class BlockingQueueTest {
    private static final int FILE_QUEUE_SIZE = 10;
    private static final int SEARCH_THREADS = 100;
    private static final File DUMMY = new File("");
    private static final BlockingQueue<File> queue = new ArrayBlockingQueue<File>(FILE_QUEUE_SIZE);

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)){
            System.out.println("Enter base directory:");
            String directory = in.nextLine();
            System.out.println("Enter keyword:");
            String keyword = in.nextLine();

            Runnable enumerator = ()->{
                try {
                    enumerate(new File(directory));
                    queue.put(DUMMY);
                } catch (InterruptedException e) {
                }
            };
            new Thread(enumerator).start();
            for (int i = 0; i < SEARCH_THREADS; i++) {
                Runnable searcher = ()->{
                    try {
                        boolean done = false;
                        while (!done) {
                            File file = queue.take();
                            if (file == DUMMY) {
                                done = true;
                                queue.put(file);
                            } else {
                                search(file, keyword);
                            }
                        }
                    } catch (InterruptedException e) {
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                };
                new Thread(searcher).start();
            }
        }
    }

    public static void enumerate(File directory) throws InterruptedException {
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory())
                enumerate(file);
            else
                queue.put(file);
        }
    }

    public static void search(File file, String keyword) throws FileNotFoundException {
        try (Scanner in = new Scanner(file, "UTF-8")){
            int lineNumber = 0;
            while (in.hasNextLine()) {
                lineNumber++;
                String line = in.nextLine();
                if (line.contains(keyword))
                    System.out.printf("%s:%d:%s%n", file.getPath(), lineNumber, line);
            }
        }
    }
}
