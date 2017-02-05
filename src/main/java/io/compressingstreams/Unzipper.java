package io.compressingstreams;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by jiangjiajie on 2017/1/29.
 */
public class Unzipper {
    public static void main(String[] args) throws IOException {
        ZipFile zf = new ZipFile(args[0]);
        Enumeration e = zf.entries();
        while (e.hasMoreElements()) {
            ZipEntry ze = (ZipEntry) e.nextElement();
            System.out.println("Unzipping " + ze.getName());
            FileOutputStream fout = new FileOutputStream(ze.getName());
            InputStream in = zf.getInputStream(ze);
            for (int c = in.read(); c != -1; c = in.read()) {
                fout.write(c);
            }
            in.close();
            fout.close();
        }
    }
}
