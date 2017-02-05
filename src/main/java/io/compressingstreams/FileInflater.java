package io.compressingstreams;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.InflaterInputStream;

/**
 * Created by jiangjiajie on 2017/1/29.
 */
public class FileInflater {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].toLowerCase().endsWith(FileDeflater.DEFLATE_SUFFIX)) {
                try {
                    FileInputStream fin = new FileInputStream(args[i]);
                    InflaterInputStream iis = new InflaterInputStream(fin);
                    FileOutputStream fout = new FileOutputStream(args[i].substring(0, args[i].length() - FileDeflater.DEFLATE_SUFFIX.length()));
                    for (int c = iis.read(); c != -1; c = iis.read()) {
                        fout.write(c);
                    }
                    fout.close();
                } catch (IOException e) {
                    System.err.println(e);
                }
            } else {
                System.err.println(args[i] + " does not apear to be a deflater file.");
            }
        }
    }
}
