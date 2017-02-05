package io.streamsinmemory;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by jiangjiajie on 2017/1/28.
 */
public class FibonacciDriver {
    public static void main(String[] args) throws IOException {
        PipedInputStream pin = new PipedInputStream();
        PipedOutputStream pout = new PipedOutputStream();
        pin.connect(pout);
        FibonacciConsumer consumer = new FibonacciConsumer(pin);
        FibonacciProducer producer = new FibonacciProducer(pout, 20);
        producer.start();
        consumer.start();
    }
}
