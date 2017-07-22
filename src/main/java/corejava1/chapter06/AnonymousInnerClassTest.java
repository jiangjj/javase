package corejava1.chapter06;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by jiangjiajie on 2017/7/15.
 */
public class AnonymousInnerClassTest {
    public static void main(String[] args) {
        TalkingClock1 clock1 = new TalkingClock1();
        clock1.start(1000, true);
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);

    }
}

class TalkingClock1 {
    public void start(int interval, boolean beep) {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("At the tone, the time is " + new Date());
                if (beep)
                    Toolkit.getDefaultToolkit().beep();
            }
        };
        Timer t = new Timer(interval, listener);
        t.start();
    }
}
