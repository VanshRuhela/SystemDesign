package org.example.example.cloneAble;

public class EmployeeClonable implements Cloneable{
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

    public EmployeeClonable(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        EmployeeClonable e1 = new EmployeeClonable("MJ" , 12);
        System.out.println(e1.getName());

        EmployeeClonable e2 = (EmployeeClonable) e1.clone();
        e2.setName("JJ");
        System.out.println(e1.getName());
        System.out.println(e2.getName());
    }
}
