package io.streamsinmemory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Vector;

/**
 * Created by jiangjiajie on 2017/1/28.
 */
public class SequencePrinter {
    public static void main(String[] args) throws IOException{
        Vector<InputStream> theStreams = new Vector<InputStream>();
        for (int i = 0; i < args.length; i++) {
            FileInputStream fin = new FileInputStream(args[i]);
            theStreams.addElement(fin);
        }
        SequenceInputStream in = new SequenceInputStream(theStreams.elements());
        for (int i = in.read(); i != -1; i = in.read()) {
            System.out.write(i);
        }
    }
}
