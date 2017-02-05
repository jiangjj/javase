package io.compressingstreams;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by jiangjiajie on 2017/1/29.
 */
public class Unzipper2 {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < args.length; i++) {
            FileInputStream fin = new FileInputStream(args[i]);
            ZipInputStream zin = new ZipInputStream(fin);
            ZipEntry ze = null;
            while ((ze = zin.getNextEntry()) != null) {
                System.out.println("Unzipping " + ze.getName());
                FileOutputStream fout = new FileOutputStream(ze.getName());
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                zin.closeEntry();
                fout.close();
            }
            zin.close();
        }
    }
}
