package nio.buffers;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.SecureRandom;

/**
 * Created by jiangjiajie on 2017/1/30.
 */
public class SecureDelete {
    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        if (file.exists()) {
            SecureRandom random = new SecureRandom();
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            FileChannel channel = raf.getChannel();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, raf.length());
            while (buffer.hasRemaining()) {
                buffer.put((byte) 0);
            }
            buffer.force();
            buffer.rewind();
            byte[] data = new byte[1];
            while (buffer.hasRemaining()) {
                random.nextBytes(data);
                buffer.put(data[0]);
            }
            buffer.force();
            file.delete();
        }
    }
}
