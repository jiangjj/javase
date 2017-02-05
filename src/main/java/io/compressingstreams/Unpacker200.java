package io.compressingstreams;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarOutputStream;
import java.util.jar.Pack200;
import java.util.zip.GZIPInputStream;

/**
 * Created by jiangjiajie on 2017/1/29.
 */
public class Unpacker200 {
    public static void main(String[] args) {
        String inName = args[0];
        String outName = null;
        if (inName.endsWith(".pack.gz")) {
            outName = inName.substring(0,inName.length() - 8);
        } else if (inName.endsWith(".pack")) {
            outName = inName.substring(0, inName.length() - 5);
        }
        JarOutputStream out = null;
        InputStream in = null;
        try {
            Pack200.Unpacker unpacker = Pack200.newUnpacker();
            out = new JarOutputStream(new FileOutputStream(outName));
            in = new FileInputStream(inName);
            if (inName.endsWith(".gz"))
                in = new GZIPInputStream(in);
            unpacker.unpack(in, out);
        } catch (IOException e) {
            System.err.println(e);
        }finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
        }
    }
}
