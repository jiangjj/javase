package io.cryptographicstreams;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

/**
 * Created by jiangjiajie on 2017/1/29.
 */
public class DigestEncryptor {
    public static void main(String[] args) throws IOException, GeneralSecurityException {
        if (args.length != 2) {
            System.err.println("Usage: java DigestEncryptor filename password");
            return;
        }
        String filename = args[0];
        String password = args[1];
        if (password.length() < 8) {
            System.err.println("Password must be at least eight characters long");
        }
        FileInputStream fin = new FileInputStream(filename);
        FileOutputStream fout = new FileOutputStream(filename + ".des");
        FileOutputStream digest = new FileOutputStream(filename + ".des.digest");
        byte[] desKeyData = password.getBytes();
        DESKeySpec desKeySpec = new DESKeySpec(desKeyData);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey desKey = keyFactory.generateSecret(desKeySpec);
        Cipher des = Cipher.getInstance("DES/ECB/PKCS5Padding");
        des.init(Cipher.ENCRYPT_MODE, desKey);
        CipherInputStream cin = new CipherInputStream(fin, des);
        MessageDigest sha = MessageDigest.getInstance("SHA");
        DigestInputStream din = new DigestInputStream(cin, sha);
        byte[] input = new byte[64];
        while (true) {
            int bytesRead = din.read(input);
            if (bytesRead == -1)
                break;
            fout.write(input, 0, bytesRead);
        }
        digest.write(sha.digest());
        digest.close();
        din.close();
        fout.flush();
        fout.close();
    }
}
