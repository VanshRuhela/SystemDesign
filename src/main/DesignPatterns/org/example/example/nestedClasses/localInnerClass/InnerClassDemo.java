package org.example.example.nestedClasses.localInnerClass;

class OuterClass{
    int outerX = 10;
    static int outerY = 20;
    private String outerStr = "Mike";

    public void print(){
        int nonFinal = 20;
        final int final_blockLvl = 40;

        class Inner{
            public void display(){

                System.out.println("outerX :" + outerX);
                System.out.println("OuterY :" + outerY);
                System.out.println("outerStr :" +outerStr);

                System.out.println("NonFinal : "+nonFinal);
                System.out.println("final : " + final_blockLvl);
            }
        }

        Inner inner = new Inner();
        inner.display();
    }
}
public class InnerClassDemo {
    public static void main(String[] a){
        OuterClass outer = new OuterClass();
        outer.print();
    }
}
