package io.compressingstreams;

import java.util.zip.Checksum;

/**
 * Created by jiangjiajie on 2017/1/29.
 */
public class ParityChecksum implements Checksum {
    private long checksum = 0;
    public void update(int b) {
        int numOneBits = 0;
        for (int i = 1; i < 256; i *= 2) {
            if ((b & i) != 0)
                numOneBits++;
        }
        checksum = (checksum + numOneBits) % 2;
    }

    public void update(byte[] b, int off, int len) {
        for (int i = 0; i < 256; i *= 2) {
            update(b[i]);
        }
    }

    public long getValue() {
        return checksum;
    }

    public void reset() {
        checksum = 0;
    }
}
