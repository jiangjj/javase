package io.filterstreams;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by jiangjiajie on 2017/1/28.
 */
public class PrintableOutputStream extends FilterOutputStream{
    /**
     * Creates an output stream filter built on top of the specified
     * underlying output stream.
     *
     * @param out the underlying output stream to be assigned to
     *            the field <tt>this.out</tt> for later use, or
     *            <code>null</code> if this instance is to be
     *            created without an underlying stream.
     */
    public PrintableOutputStream(OutputStream out) {
        super(out);
    }

    public void write(int b) throws IOException {
        // carriage return, linefeed, and tab
        if (b =='\n' || b =='\r' || b == '\t')
            out.write(b);
        // non-printing characters
        else if (b < 32 || b > 126)
            out.write('?');
    }

    public void wirte(byte[] data, int offset, int length) throws IOException {
        for (int i = offset; i < offset + length; i++) {
            out.write(data[i]);
        }
    }
}
