package io.compressingstreams;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

/**
 * Created by jiangjiajie on 2017/1/29.
 */
public class FileSummer {
    public static void main(String[] args) throws IOException {
        FileInputStream fin = new FileInputStream(args[0]);
        System.out.println(args[0] + ":\t" + getCRC32(fin));
        fin.close();
    }
    public static long getCRC32(InputStream in) throws  IOException {
        Checksum cs = new CRC32();
        for (int b = in.read(); b != -1; b = in.read()) {
            cs.update(b);
        }
        return cs.getValue();
    }
}
