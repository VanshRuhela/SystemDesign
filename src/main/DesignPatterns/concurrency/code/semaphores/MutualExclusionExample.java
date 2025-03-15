package concurrency.code.semaphores;

import java.util.concurrent.Semaphore;

public class MutualExclusionExample {
    // Shared variable
    private static int count = 0;

    // Semaphore to enforce mutual exclusion
    private static final Semaphore semaphore = new Semaphore(1); // Initialize with 1 to allow one thread at a time

    public static void main(String[] args) {
        // Thread A
        Thread threadA = new Thread(() -> {
            System.out.println("Thread A");
            for (int i = 0; i < 100; i++) {
                try {
                    semaphore.acquire(); // Acquire the semaphore before accessing count
                    System.out.println("Thread A acquired");

                    count = count + 1; // Critical section
                    semaphore.release(); // Release the semaphore after updating count
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Thread B
        Thread threadB = new Thread(() -> {
            System.out.println("Thread B");

            for (int i = 0; i < 100; i++) {
                try {
                    semaphore.acquire(); // Acquire the semaphore before accessing count
                    System.out.println("Thread B acquired");
                    count = count + 1; // Critical section
                    semaphore.release(); // Release the semaphore after updating count
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start both threads
        threadA.start();
        threadB.start();

        // Wait for both threads to finish
        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the final value of count
        System.out.println("Final count: " + count);
    }
}
