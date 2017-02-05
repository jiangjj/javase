package nio.buffers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by jiangjiajie on 2017/1/30.
 */
public class RootsChannel {
    final static int SIZE_OF_DOUBLE = 8;
    final static int LENGTH = 1001;
    public static void main(String[] args) throws IOException {
        ByteBuffer data = ByteBuffer.allocate(SIZE_OF_DOUBLE * LENGTH);
        DoubleBuffer roots = data.asDoubleBuffer();
        while (roots.hasRemaining()) {
            roots.put(Math.sqrt(roots.position()));
        }
        FileOutputStream fout = new FileOutputStream("roots.dat");
        FileChannel outChannel = fout.getChannel();
        outChannel.write(data);
        outChannel.close();
    }
}
