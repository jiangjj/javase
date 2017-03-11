package corejava.chapter03;

import java.util.Scanner;

/**
 * Created by Administrator on 2017/3/9.
 */
public class InputTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //get first input
        System.out.print("What is your name? ");
        String name = in.nextLine();

        //get second input
        System.out.print("How old are you? ");
        int age = in.nextInt();

        System.out.println("Hello, " + name + ".Next year, you'll be " + (age + 1));
    }
}
