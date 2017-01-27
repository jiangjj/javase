package io.inputstreams;

import java.io.IOException;

/**
 * Created by Administrator on 2017/1/27.
 */
public class StreamPrinter {
    public static void main(String[] args) {
        try {
            while(true) {
                int datum = System.in.read();
                if (datum == -1)
                    break;
                System.out.println(datum);
            }
        } catch (IOException ex) {
            System.err.println("Couldn't read from System.in!");
        }
    }
}
