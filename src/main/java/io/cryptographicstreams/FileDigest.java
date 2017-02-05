package io.cryptographicstreams;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by jiangjiajie on 2017/1/29.
 */
public class FileDigest {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        if (args.length != 2) {
            System.err.println("Usage: java FileDigest url filename");
            return;
        }
        URL u = new URL(args[0]);
        FileOutputStream out = new FileOutputStream(args[1]);

        out.close();
    }
    public static void copyFileWithDigest(InputStream in, OutputStream out) throws IOException, NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA-512");
        DigestOutputStream dout = new DigestOutputStream(out, sha);
        byte[] data = new byte[128];
        while (true) {
            int bytesRead = in.read(data);
            if (bytesRead < 0)
                break;
            dout.write(data, 0, bytesRead);
        }
        dout.flush();
        byte[] result = dout.getMessageDigest().digest();
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
    }
}
