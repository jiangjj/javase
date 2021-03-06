package corejava2.chapter02;

import java.io.*;

/**
 * Created by jiangjiajie on 2017/7/20.
 */
public class ObjectStreamTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        Employee harry = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        Manager carl = new Manager("Carl Cracker", 80000, 1987, 12, 15);
        carl.setSecretary(harry);
        Manager tony = new Manager("Tony Tester", 40000, 1990, 3, 15);
        tony.setSecretary(harry);

        Employee[] staff = new Employee[3];

        staff[0] = carl;
        staff[1] = harry;
        staff[2] = tony;

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("employee.dat"))){
            out.writeObject(staff);
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("employee.dat"))){
            Employee[] newStaff = (Employee[]) in.readObject();
            newStaff[1].raiseSalary(10);
            for (Employee e : newStaff)
                System.out.println(e);
            carl = (Manager) newStaff[0];
            tony = (Manager) newStaff[2];
            System.out.println(carl.getSecretary() == tony.getSecretary());
        }
    }
}
