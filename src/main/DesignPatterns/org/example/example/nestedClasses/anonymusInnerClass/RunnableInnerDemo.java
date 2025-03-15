package org.example.example.nestedClasses.anonymusInnerClass;

public class RunnableInnerDemo {
    public static void main(String [] a){
        System.out.println(Thread.currentThread().getName());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread " + Thread.currentThread().getName());
            }
        });
        thread.setName("Run");
        thread.start();
    }
}
