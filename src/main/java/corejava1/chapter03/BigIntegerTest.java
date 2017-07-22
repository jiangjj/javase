package corejava1.chapter03;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/3/9.
 */
public class BigIntegerTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("How many numbers do you need to draw?");
        int k = in.nextInt();
        System.out.println("What is the higest number you can draw?");
        int n = in.nextInt();

        BigInteger lotteryOdds = BigInteger.ONE;
        for (int i = 1; i <= k; i++) {
            lotteryOdds = lotteryOdds.multiply(BigInteger.valueOf(n - i + 1)).divide(BigInteger.valueOf(i));
        }
        System.out.println("Your odds are 1 in " + lotteryOdds + ". Good luck!");
    }
}
