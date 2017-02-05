package io.cryptographicstreams;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by jiangjiajie on 2017/1/29.
 */
public class FileDecryptor {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java FIleDecryptor infile outfile password");
            return;
        }
        String infile = args[0];
        String outfile = args[1];
        String password = args[2];
        if (password.length() < 8) {
            System.err.println("Password must be at least eight characters long");
        }
        try {
            FileInputStream fin = new FileInputStream(infile);
            FileOutputStream fout = new FileOutputStream(outfile);
            byte[] desKeyData = password.getBytes();
            DESKeySpec desKeySpec = new DESKeySpec(desKeyData);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey desKey = keyFactory.generateSecret(desKeySpec);
            DataInputStream din = new DataInputStream(fin);
            int ivSize = din.readInt();
            byte[] iv = new byte[ivSize];
            din.readFully(iv);
            IvParameterSpec ivps = new IvParameterSpec(iv);
            Cipher des = Cipher.getInstance("DES/CBC/PKCS5Padding");
            des.init(Cipher.DECRYPT_MODE, desKey, ivps);
            byte[] input = new byte[64];
            while (true) {
                int bytesRead = fin.read(input);
                if (bytesRead == -1)
                    break;
                byte[] output = des.update(input, 0, bytesRead);
                if (output != null)
                    fout.write(output);
            }
            byte[] output = des.doFinal();
            if (output != null)
                fout.write(output);
            fin.close();
            fout.flush();
            fout.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
