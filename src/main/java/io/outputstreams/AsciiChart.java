package io.outputstreams;

/**
 * Created by Administrator on 2017/1/27.
 */
public class AsciiChart {
    public static void main(String[] args) {
        for (int i = 32; i < 127; i++) {
            System.out.write(i);
            // break line after every eight characters.
            if (i % 8 == 7)
                //System.out.write('\n');
                System.out.write(10);
            else
                //System.out.write('\t');
                System.out.write(9);
        }
        System.out.write('\n');
    }
}
