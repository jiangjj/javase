package io.outputstreams;

/**
 * Created by Administrator on 2017/1/27.
 */
public class AsciiArray {
    public static void main(String[] args) {
        byte[] b = new byte[(127 - 31)*2];
        int index = 0;
        for (int i = 32; i < 127; i++) {
            b[index++] = (byte) i;
            if (i % 8 == 7)
                b[index++] = (byte) '\n';
            else
                b[index++] = (byte) '\t';
        }
        b[index++] = (byte) '\n';
        try {
            System.out.write(b);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
