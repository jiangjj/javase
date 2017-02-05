package io.cryptographicstreams;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by jiangjiajie on 2017/1/29.
 */
public class FileEncryptor {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java FileEncryptor filename password");
            return;
        }
        String filename = args[0];
        String password = args[1];
        if (password.length() < 8) {
            System.err.println("Password must be at least eight characters long");
        }
        try {
            FileInputStream fin = new FileInputStream(args[0]);
            FileOutputStream fout = new FileOutputStream(args[0] + ".des");
            byte[] desKeyData = password.getBytes();
            DESKeySpec desKeySpec = new DESKeySpec(desKeyData);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey desKey = keyFactory.generateSecret(desKeySpec);
            Cipher des = Cipher.getInstance("DES/CBC/PKCS5Padding");
            des.init(Cipher.ENCRYPT_MODE, desKey);
            byte[] iv = des.getIV();
            DataOutputStream dout = new DataOutputStream(fout);
            dout.writeInt(iv.length);
            dout.write(iv);
            byte[] input = new byte[64];
            while (true) {
                int bytesRead = fin.read(input);
                if (bytesRead == -1)
                    break;
                byte[] output = des.update(input, 0, bytesRead);
                if (output != null)
                    dout.write(output);
            }
            byte[] output = des.doFinal();
            if (output != null)
                dout.write(output);
            fin.close();
            dout.flush();
            dout.close();
        }catch (Exception e) {
            System.err.println(e);
        }
    }
}
