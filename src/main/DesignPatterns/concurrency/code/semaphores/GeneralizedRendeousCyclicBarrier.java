package concurrency.code.semaphores;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class GeneralizedRendeousCyclicBarrier {
    // Number of threads that need to reach the rendezvous point
    private static final int NUMBER_OF_THREADS = 5;
    // CyclicBarrier to synchronize threads
    private static final CyclicBarrier barrier = new CyclicBarrier(NUMBER_OF_THREADS, () -> {
        // This runs after all threads reach the barrier
        System.out.println("All threads have reached the rendezvous point.");
    });

    public static void main(String[] args) {
        // Create and start multiple threads
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            final int threadNumber = i + 1; // For display purposes
            Thread thread = new Thread(() -> {
                try {
                    System.out.println("Thread " + threadNumber + " is performing some work before rendezvous.");
                    // Simulating some work with Thread.sleep
                    Thread.sleep((long) (Math.random() * 1000));

                    // Rendezvous point
                    barrier.await(); // Wait for other threads to reach the barrier

                    // Critical point
                    System.out.println("Thread " + threadNumber + " is in the critical point.");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
            thread.start(); // Start the thread
        }
    }
}
