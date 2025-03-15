package org.example.example.enums;

enum Employee {

    Dave("25") {
        @Override
        public String toLowerCae() {
            return null;
        }
    }, John("20") {
        @Override
        public String toLowerCae() {
            return null;
        }
    }, Lisa("12") {
        @Override
        public String toLowerCae() {
            return null;
        }
    }, Mike("212") {
        @Override
        public String toLowerCae() {
            return null;
        }
    };
    private String age;

    String getAge() {
        return age;
    }

    Employee(String age) {
        this.age = age;
        System.out.println("Constructor called for: " + this.toString());
    }

    public abstract String toLowerCae();
}

public class Test {
    public static void main(String[] args) {
        System.out.println("Age of lisa " + Employee.Lisa.getAge());
    }
}
