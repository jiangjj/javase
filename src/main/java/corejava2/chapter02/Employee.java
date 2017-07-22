package corejava2.chapter02;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by jiangjiajie on 2017/7/20.
 */
public class Employee extends SerialCloneable{
    public static final int NAME_SIZE = 40;
    public static final int RECORD_SIZE = 100;
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee() {
    }

    public Employee(String name, double salary, LocalDate hireDay) {
        this.name = name;
        this.salary = salary;
        this.hireDay = hireDay;
    }

    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        this.hireDay = LocalDate.of(year, month, day);
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void setHireDay(LocalDate hireDay) {
        this.hireDay = hireDay;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay +
                '}';
    }
}
