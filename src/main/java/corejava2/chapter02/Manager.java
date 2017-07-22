package corejava2.chapter02;

import java.io.Serializable;

/**
 * Created by jiangjiajie on 2017/7/20.
 */
public class Manager extends Employee implements Serializable{
    private double bonus;
    private Employee secretary;
    public Manager(String name, double salary, int year, int month, int day) {
        super(name, salary, year, month, day);
        this.bonus = 0;
    }

    public double getSalary() {
        double baseSalary = super.getSalary();
        return baseSalary + bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public Employee getSecretary() {
        return secretary;
    }

    public void setSecretary(Employee secretary) {
        this.secretary = secretary;
    }
}
