package io.outputstreams;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/1/27.
 */
public class NullOutputStream extends OutputStream{
    private boolean closed = false;
    public void write(int b) throws IOException {
        if (closed)
            throw new IOException("Write to closed stream");
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        if (b == null)
            throw new NullPointerException("b is null");
        if (closed)
            throw new IOException("Write to closed stream");
    }

    @Override
    public void close() throws IOException {
        closed = true;
    }
}
