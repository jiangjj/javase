package corejava1.chapter08;

/**
 * Created by jiangjiajie on 2017/7/15.
 */
public class Employee {
    private String name;

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee(String name) {

        this.name = name;
    }
}
