package concurrency.code.semaphores;

import java.util.concurrent.Semaphore;

import java.util.concurrent.Semaphore;

public class GenreralizedRendeveou {
    private static final int n = 5; // Number of threads
    private static final Semaphore mutex = new Semaphore(1); // For mutual exclusion
    private static final Semaphore barrier = new Semaphore(0); // For barrier synchronization
    private static int count = 0; // Counter for threads at rendezvous

    public static void main(String[] args) {
        // Create and start multiple threads
        for (int i = 0; i < n; i++) {
            Thread thread = new Thread(new Worker());
            thread.start();
        }
    }

    static class Worker implements Runnable {
        @Override
        public void run() {
            // Rendezvous logic
            try {
                mutex.acquire(); // Enter critical section to update count
                System.out.println("mutex acquired");
                    count++;

                    if (count == n) {
                        // If all threads have reached the rendezvous
//                        System.out.println("inside");
                        barrier.release(); // Release barrier for all waiting threads
                    }
                    // Exit critical section

                    barrier.acquire(); // Wait at the barrier

                    barrier.release();
                mutex.release();
                // Now in the critical point
                System.out.println(Thread.currentThread().getName() + " is in the critical point.");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}