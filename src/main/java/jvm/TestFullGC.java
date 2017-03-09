package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangjiajie on 2017/2/18.
 */
public class TestFullGC {
    public static void main(String[] args) throws Exception {
        List<MemoryObject> objects = new ArrayList<>(6);
        for (int i = 0; i < 10; i++) {
            objects.add(new MemoryObject(1024 * 1024));
        }
        //让上面的对象尽可能地转入旧生代中
        System.gc();
        System.gc();
        Thread.sleep(2000);
        objects.clear();
        for (int i = 0; i < 10; i++) {
            objects.add(new MemoryObject(1024 * 1024));
            if (i % 3 == 0) {
                objects.remove(0);
            }
        }
        Thread.sleep(5000);
    }

}
