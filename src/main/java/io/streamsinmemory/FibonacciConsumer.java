package io.streamsinmemory;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jiangjiajie on 2017/1/28.
 */
public class FibonacciConsumer extends Thread{
    private DataInputStream theInput;

    public FibonacciConsumer(InputStream in) {
        theInput = new DataInputStream(in);
    }

    @Override
    public void run() {
        try {
            while (true)
                System.out.println(theInput.readInt());
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}
