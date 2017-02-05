package io.compressingstreams;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by jiangjiajie on 2017/1/29.
 */
public class Zipper {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: java Zipper [-d level] name.zip" + " file1 file2...");
            return;
        }
        String outputFile = args[0];
        int level = 9;
        int start = 1;
        if (args[0].equals("-d")) {
            try {
                level = Integer.parseInt(args[1]);
                outputFile = args[2];
                start = 3;
            } catch (Exception e) {
                System.out.println("Usage: java Zipper [-d level] name.zip" + " file1 file2...");
                return;
            }
        }
        FileOutputStream fout = new FileOutputStream(outputFile);
        ZipOutputStream zout = new ZipOutputStream(fout);
        zout.setLevel(level);
        for (int i = start; i < args.length; i++) {
            ZipEntry ze = new ZipEntry(args[i]);
            FileInputStream fin = new FileInputStream(args[i]);
            try {
                System.out.println("Compressing " + args[i]);
                zout.putNextEntry(ze);
                for (int c = fin.read(); c != -1; c = fin.read()) {
                    zout.write(c);
                }
            } finally {
                fin.close();
            }
        }
        zout.close();
    }
}
