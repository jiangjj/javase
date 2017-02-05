package io.objectstreams;

import javax.crypto.Cipher;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by jiangjiajie on 2017/1/30.
 */
public class SealedPoint {
    public static void main(String[] args) throws Exception {
        Point tdp = new Point(32, 45);
        FileOutputStream fout = new FileOutputStream("point.des");
        ObjectOutputStream oout = new ObjectOutputStream(fout);
        byte[] desKeyData = {(byte) 0x90, (byte) 0x67, (byte) 0x3E, (byte) 0xE6,
                (byte) 0x42, (byte) 0x15, (byte) 0x7A, (byte) 0xA3 };
        DESKeySpec desKeySpec = new DESKeySpec(desKeyData);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey desKey = keyFactory.generateSecret(desKeySpec);
// Use Data Encryption Standard.
        Cipher des = Cipher.getInstance("DES/ECB/PKCS5Padding");
        des.init(Cipher.ENCRYPT_MODE, desKey);
        SealedObject so = new SealedObject(tdp, des);
        oout.writeObject(so);
        oout.close();
    }
}
