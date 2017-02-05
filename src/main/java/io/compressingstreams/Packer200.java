package io.compressingstreams;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.jar.JarFile;
import java.util.jar.Pack200;

/**
 * Created by jiangjiajie on 2017/1/29.
 */
public class Packer200 {
    public static void main(String[] args) {
        OutputStream out = null;
        try {
            JarFile jf = new JarFile(args[0]);
            Pack200.Packer packer = Pack200.newPacker();
            out = new FileOutputStream(args[0] + ".pack");
            packer.pack(jf, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
