package nio.channels;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by jiangjiajie on 2017/1/30.
 */
public class NIOCat {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Usage: java NIOCat inFile1 inFile2... outFile");
            return;
        }
        ByteBuffer[] buffers = new ByteBuffer[args.length-1];
        for (int i = 0; i < args.length-1; i++) {
            RandomAccessFile raf = new RandomAccessFile(args[i], "r");
            FileChannel channel = raf.getChannel();
            buffers[i] = channel.map(FileChannel.MapMode.READ_ONLY, 0, raf.length());
        }
        FileOutputStream outFile = new FileOutputStream(args[args.length-1]);
        FileChannel out = outFile.getChannel();
        out.write(buffers);
        out.close();
    }
}
