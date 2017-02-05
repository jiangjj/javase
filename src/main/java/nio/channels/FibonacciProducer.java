package nio.channels;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

/**
 * Created by jiangjiajie on 2017/1/30.
 */
public class FibonacciProducer extends Thread{
    private WritableByteChannel out;
    private int howMany;

    public FibonacciProducer(WritableByteChannel out, int howMany) {
        this.out = out;
        this.howMany = howMany;
    }

    @Override
    public void run() {
        BigInteger low = BigInteger.ONE;
        BigInteger high = BigInteger.ONE;
        try {
            ByteBuffer buffer = ByteBuffer.allocate(4);
            buffer.putInt(howMany);
            buffer.flip();
            while (buffer.hasRemaining())
                out.write(buffer);
            for (int i = 0; i < howMany; i++){
                byte[] data = low.toByteArray();
                buffer = ByteBuffer.allocate(4 + data.length);
                buffer.putInt(data.length);
                buffer.put(data);
                buffer.flip();
                while (buffer.hasRemaining())
                    out.write(buffer);
                BigInteger temp = high;
                high = high.add(low);
                low = temp;
            }
            out.close();
            System.out.println("Closed");
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
