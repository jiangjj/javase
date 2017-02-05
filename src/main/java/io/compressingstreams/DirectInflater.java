package io.compressingstreams;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.Inflater;

/**
 * Created by jiangjiajie on 2017/1/29.
 */
public class DirectInflater {
    public static void main(String[] args) {
        Inflater inf = new Inflater();
        byte[] input = new byte[1024];
        byte[] output = new byte[1024];
        for (int i = 0; i < args.length; i++) {
            try {
                if (!args[i].endsWith(DirectDeflater.DEFLATE_SUFFIX)) {
                    System.err.println(args[i] + " does not look like a deflated file");
                    continue;
                }
                FileInputStream fin = new FileInputStream(args[i]);
                FileOutputStream fout = new FileOutputStream(args[i].substring(0, args[i].length() - DirectDeflater.DEFLATE_SUFFIX.length()));
                while (true) {
                    int numRead = fin.read(input);
                    if (numRead != -1) {
                        inf.setInput(input, 0, numRead);
                    }
                    int numDecompressed = 0;
                    while ((numDecompressed = inf.inflate(output, 0, output.length)) != 0) {
                        fout.write(output, 0, numDecompressed);
                    }
                    if (inf.finished()) {
                        break;
                    } else if (inf.needsDictionary()) {
                        System.err.println("Dictionary required bailing...");
                        break;
                    } else if (inf.needsInput()) {
                        continue;
                    }
                }
                fin.close();
                fout.flush();
                fout.close();
                inf.reset();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}
