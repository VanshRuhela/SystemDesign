package org.example.example.cloneAble.deepCopyShallowCopy;

class Company{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company(String name) {
        this.name = name;
    }

    private String name;
}
public class Employeee implements Cloneable{
    private String name;
    private int age;
    private Company company;

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Employeee(String name, int age, Company company) {
        this.name = name;
        this.age = age;
        this.company = company;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Company c = new Company("SCB");
        Employeee e = new Employeee("MJ", 10, c);
        System.out.println("Employee 1 , company name : "+e.getCompany().getName());

        Employeee e2 = (Employeee) e.clone();
        System.out.println("Employee 2 , company name : "+e2.getCompany().getName());

        e2.getCompany().setName("Google");
        System.out.println("_________________");
        System.out.println("Employee 1 , company name : "+e.getCompany().getName());
        System.out.println("Employee 2 , company name : "+e2.getCompany().getName());

    }
}
