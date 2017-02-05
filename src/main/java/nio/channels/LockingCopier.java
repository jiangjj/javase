package nio.channels;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Created by jiangjiajie on 2017/1/30.
 */
public class LockingCopier {
    public static void main(String[] args) throws IOException {
        FileInputStream inFile = new FileInputStream(args[0]);
        FileOutputStream outFile = new FileOutputStream(args[1]);
        FileChannel inChannel = inFile.getChannel();
        FileChannel outChannel = outFile.getChannel();
        FileLock inLock = inChannel.lock(0, inChannel.size(), true);
        FileLock outLock = outChannel.lock();
        inChannel.transferTo(0, inChannel.size(), outChannel);
        outLock.release();
        inLock.release();
        inChannel.close();
        outChannel.close();
    }
}
