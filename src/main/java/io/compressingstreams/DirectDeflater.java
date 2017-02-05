package io.compressingstreams;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;

/**
 * Created by jiangjiajie on 2017/1/29.
 */
public class DirectDeflater {
    public final static String DEFLATE_SUFFIX = ".dfl";
    public static void main(String[] args) throws IOException {
        Deflater def = new Deflater();
        byte[] input = new byte[1024];
        byte[] output = new byte[1024];
        for (int i = 0; i < args.length; i++) {
            FileInputStream fin = new FileInputStream(args[i]);
            FileOutputStream fout = new FileOutputStream(args[i] + DEFLATE_SUFFIX);
            while (true) {
                int numRead = fin.read(input);
                if (numRead == -1) {
                    def.finish();
                    while (!def.finished()) {
                        int numCompressedBytes = def.deflate(output, 0, output.length);
                        if (numCompressedBytes > 0) {
                            fout.write(output, 0, numCompressedBytes);
                        }
                    }
                    break;
                } else {
                    def.setInput(input, 0, numRead);
                    while (!def.needsInput()) {
                        int numCompressedBytes = def.deflate(output, 0, output.length);
                        if (numCompressedBytes > 0) {
                            fout.write(output, 0, numCompressedBytes);
                        }
                    }
                }
            }
            fin.close();
            fout.flush();
            fout.close();
            def.reset();
        }
    }
}
