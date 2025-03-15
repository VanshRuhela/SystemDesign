package org.example.example.nestedClasses.anonymusInnerClass;

import java.util.*;

public class AnonymousInnerDemo {
    public static void main(String[] a){
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(4, "A", 25, 26000));
        employeeList.add(new Employee(5, "X", 21, 28000));
        employeeList.add(new Employee(6, "V", 23, 27000));

        employeeList.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        System.out.println(employeeList);


    }
}
