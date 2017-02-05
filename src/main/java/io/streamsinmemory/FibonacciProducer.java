package io.streamsinmemory;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by jiangjiajie on 2017/1/28.
 */
public class FibonacciProducer extends Thread{
    private DataOutputStream theOutput;
    private int howMany;
    public FibonacciProducer(OutputStream out, int howMany) {
        theOutput = new DataOutputStream(out);
        this.howMany = howMany;
    }

    @Override
    public void run() {
        try {
            int f1 = 1;
            int f2 = 1;
            theOutput.writeInt(f1);
            for (int i = 2; i < howMany; i++) {
                int temp = f2;
                f2 = f2 + f1;
                f1 = temp;
                if (f2 < 0)
                    break;
                theOutput.writeInt(f2);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
