package io.streamsinmemory;

import java.io.*;

/**
 * Created by jiangjiajie on 2017/1/28.
 */
public class FibonacciFile {
    public static void main(String[] args) throws IOException {
        int howMany = 20;
        ByteArrayOutputStream bout = new ByteArrayOutputStream(howMany*4);
        DataOutputStream dout = new DataOutputStream(bout);
        int f1 = 1;
        int f2 = 1;
        dout.writeInt(f1);
        dout.writeInt(f2);
        for (int i = 3; i <= 20; i++) {
            int temp = f2;
            f2 = f2 + f1;
            f1 = temp;
            dout.writeInt(f2);
        }
        FileOutputStream fout = new FileOutputStream("fibonacci,dat");
        try {
            bout.writeTo(fout);
            fout.flush();
        } finally {
            fout.close();
        }
    }
}
