package corejava1.chapter09;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiangjiajie on 2017/7/15.
 */
public class MapTest {
    public static void main(String[] args) {
        Map<String, String> staff = new HashMap<>();
        staff.put("001", "Amy Lee");
        staff.put("002", "Harry Hacker");
        staff.put("003", "Gary Cooper");

        System.out.println(staff);

        staff.remove("001");

        staff.put("004", "Francesca Miller");

        System.out.println(staff.get("002"));

        staff.forEach((k, v) -> {
            System.out.println("key=" + k + ", value=" + v);
        });
    }
}
