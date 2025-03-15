package org.example.example;
class HelloWorld {
    public void print(int a, int b){
        System.out.println(a+b);
    }

    public void print(int a , long b){

        System.out.println("from long " + (a+b));
    }
    public static void main(String[] args) {
        HelloWorld o = new HelloWorld();
        o.print(1, 2L);
    }
}
