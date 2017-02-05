package nio.buffers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by jiangjiajie on 2017/1/30.
 */
public class NIODuplicator {
    public static void main(String[] args) throws IOException {
        FileInputStream inFile = new FileInputStream(args[0]);
        FileOutputStream outFile = new FileOutputStream(args[1]);
        FileChannel inChannel = inFile.getChannel();
        FileChannel outChannel = outFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024*1024);
        int bytesRead = 0;
        while (bytesRead >= 0 || buffer.hasRemaining()) {
            if (bytesRead != -1)
                bytesRead = inChannel.read(buffer);
            buffer.flip();
            outChannel.write(buffer);
            buffer.compact();
        }
        inChannel.close();
        outChannel.close();
    }
}
