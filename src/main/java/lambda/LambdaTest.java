package lambda;

import javax.swing.*;
import java.util.Arrays;
import java.util.Date;
import java.util.function.BiFunction;

/**
 * Created by Administrator on 2017/2/5.
 */
public class LambdaTest {
    public static void main(String[] args) {
        String[] planets = {"Mecury", "Venus", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"};
        BiFunction<String, String, Integer> comp = (first, second) -> first.length() - second.length();
        System.out.println(Arrays.toString(planets));
        System.out.println("Sorted in dictionary order:");
        Arrays.sort(planets);
        System.out.println(Arrays.toString(planets));
        System.out.println("Sorted by length:");
        Arrays.sort(planets, (first, second) -> first.length() - second.length());
        System.out.println(Arrays.toString(planets));

        Timer t = new Timer(1000, event -> System.out.println("The time is " + new Date()));
        t.start();
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}
