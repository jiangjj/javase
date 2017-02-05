package io.cryptographicstreams;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by jiangjiajie on 2017/1/29.
 */
public class URLDigest {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        URL u = new URL(args[0]);
        InputStream in = u.openStream();
        MessageDigest sha = MessageDigest.getInstance("SHA");
        byte[] data = new byte[128];
        while (true) {
            int bytesRead = in.read(data);
            if (bytesRead < 0)
                break;
            sha.update(data, 0, bytesRead);
        }
        byte[] result = sha.digest();
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i] + " ");
        }
        System.out.println();
        System.out.println(new BigInteger(result));
    }
}
