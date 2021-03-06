package io.inputstreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/1/27.
 */
public class StreamCopier {
    public static void main(String[] args) {
        try {
            copy(System.in, System.out);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public static void copy(InputStream in, OutputStream out) throws  IOException {
        byte[] buffer = new byte[1024];
        while(true) {
            int bytesRead = in.read(buffer);
            if (bytesRead == -1)
                break;
            out.write(buffer, 0, bytesRead);
        }
    }
}
