package io.datastreams;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by jiangjiajie on 2017/1/28.
 */
public class IntReader {
    public static void main(String[] args)  throws IOException {
        DataInputStream din = null;
        try {
            FileInputStream fin = new FileInputStream(args[0]);
            System.out.println("---------" + args[0] + "----------");
            din = new DataInputStream(fin);
            while (true) {
                int theNumber = din.readInt();
                System.out.println(theNumber);
            }
        } catch (EOFException ex) {
            din.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
