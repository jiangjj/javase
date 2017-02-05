package nio.channels;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by jiangjiajie on 2017/1/30.
 */
public class NIOTransfer {
    public static void main(String[] args) throws IOException {
        FileInputStream inFile = new FileInputStream(args[0]);
        FileOutputStream outFile = new FileOutputStream(args[1]);
        FileChannel inChannel = inFile.getChannel();
        FileChannel outChannel = outFile.getChannel();
        inChannel.transferTo(0, inChannel.size(), outChannel);
        inChannel.close();
        outChannel.close();
    }
}
