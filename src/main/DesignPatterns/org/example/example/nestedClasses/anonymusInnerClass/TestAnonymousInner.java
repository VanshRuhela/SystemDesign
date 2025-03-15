package org.example.example.nestedClasses.anonymusInnerClass;

public class TestAnonymousInner {
    private int x = 10;
    private static String HELLO = "Hello";

    public static void main() {
        System.out.println("overloaded Main method");
    }

    public void dummy(){
        System.out.println("Dummy");
    }

    private void print(){
        int y = 20;
        final int z = 30;
        int w = 40;

        Parent p = new Parent(){
            static final int w = 50;
            public void display() {
                System.out.println("Display of inner");
                System.out.println("Enclosing class x : " + x);
                System.out.println("Enclosing class constant : " + HELLO);
                main();
                dummy();
                System.out.println("Enclosing block y: " + y);
                System.out.println("Enclosing block z: " + z);
                System.out.println("w variable shadowing: " + w);
            }
        };
        p.display();
    }

    public static void main(String[] args) {
        TestAnonymousInner o = new TestAnonymousInner();
        o.print();
    }
}
