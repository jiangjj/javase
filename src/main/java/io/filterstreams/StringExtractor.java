package io.filterstreams;

import java.io.*;

/**
 * Created by jiangjiajie on 2017/1/28.
 */
public class StringExtractor {
    public static void main(String [] args) {
        if (args.length < 1) {
            System.out.println("Usage: java StringExtractor inFile");
            return;
        }
        try {
            InputStream in = new FileInputStream(args[0]);
            OutputStream out = null;
            if (args.length >= 2) {
                out = new FileOutputStream(args[1]);
            } else {
                out = System.out;
            }
            PrintableOutputStream pout = new PrintableOutputStream(out);
            for (int c = in.read(); c != -1; c = in.read()) {
                pout.write(c);
            }
            out.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Usage: java StringExtractor inFile outFile");
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
