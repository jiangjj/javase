package io.compressingstreams;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipFile;

/**
 * Created by jiangjiajie on 2017/1/29.
 */
public class ZipLister {
    public static void main(String[] args) throws IOException {
        ZipFile zf = new ZipFile(args[0]);
        Enumeration e = zf.entries();
        while (e.hasMoreElements()) {
            System.out.println(e.nextElement());
        }
    }
}
