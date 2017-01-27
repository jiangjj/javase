package io.filestreams;

import io.inputstreams.StreamCopier;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/1/27.
 */
public class FileTyper {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java FileTyper filename");
            return;
        }
        typeFile(args[0]);
    }

    public static void typeFile(String filename) throws IOException {
        FileInputStream fin = new FileInputStream(filename);
        try {
            StreamCopier.copy(fin, System.out);
        }
        finally {
            fin.close();
        }
    }
}

