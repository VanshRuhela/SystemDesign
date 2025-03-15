package org.example.example.cloneAble;

import org.example.example.nestedClasses.anonymusInnerClass.Employee;

public class EmployeeWithoutClonable {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public EmployeeWithoutClonable(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
       EmployeeWithoutClonable e1 = new EmployeeWithoutClonable("MJ" , 1);
        System.out.println(e1.age);
        EmployeeWithoutClonable e2 = e1;
        e2.setAge(12);
        System.out.println(e1.age);
    }
}
