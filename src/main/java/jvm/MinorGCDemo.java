package jvm;

/**
 * Created by jiangjiajie on 2017/2/17.
 */
public class MinorGCDemo {
    public static void main(String[] args) throws Exception {
//        MemoryObject object = new MemoryObject(1024 * 1024);
//        for (int i = 0; i < 2; i++) {
//            happenMinorGC(11);
//            Thread.sleep(2000);
//        }

        //Minor GC时survivor空间不足，对象直接进入旧生代的示例
//        MemoryObject object = new MemoryObject(1024 * 1024);
//        MemoryObject m2Object = new MemoryObject(1024 * 1024 * 2);
//        happenMinorGC(9);
//        Thread.sleep(2000);

        //不同GC的日志示例
        MemoryObject object = new MemoryObject(1024 *1024);
        happenMinorGC(11);
        Thread.sleep(2000);
    }

    private static void happenMinorGC(int happenMinorGCIndex) throws  Exception{
        for (int i = 0; i < happenMinorGCIndex; i++) {
            if (i == happenMinorGCIndex - 1) {
                Thread.sleep(2000);
                System.out.println("minor gc should happen");
            }
            new MemoryObject(1024 * 1024);
        }
    }

}

class MemoryObject {
    private byte[] bytes;
    public MemoryObject(int objectSize) {
        this.bytes = new byte[objectSize];
    }
}