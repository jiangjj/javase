package io.filterstreams;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jiangjiajie on 2017/1/28.
 */
public class PrintableInputStream extends FilterInputStream{
    /**
     * Creates a <code>FilterInputStream</code>
     * by assigning the  argument <code>in</code>
     * to the field <code>this.in</code> so as
     * to remember it for later use.
     *
     * @param in the underlying input stream, or <code>null</code> if
     *           this instance is to be created without an underlying stream.
     */
    protected PrintableInputStream(InputStream in) {
        super(in);
    }

    public int read() throws IOException {
        int b = in.read();
        // printing, ASCII characters
        if (b >= 32 && b <= 126)
            return b;
        else if (b == '\n' || b == '\r' || b == '\t')
            return b;
        else
            return '?';
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int result = in.read(b, off, len);
        for (int i = off; i < off + result; i++) {
            if (b[i] == '\n' || b[i] == '\r' || b[i] == '\t')
                ;
            else if (b[i] < 32 || b[i] > 126)
                b[i] = (byte) '?';
        }
        return result;
    }
}
