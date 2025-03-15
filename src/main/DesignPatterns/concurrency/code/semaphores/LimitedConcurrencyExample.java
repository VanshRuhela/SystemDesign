package concurrency.code.semaphores;

import java.util.concurrent.Semaphore;

public class LimitedConcurrencyExample {
    // Shared variable
    private static int count = 0;

    // Semaphore to enforce a limit on the number of concurrent threads
    private static final int MAX_CONCURRENT_THREADS = 5; // Limit the number of concurrent threads
    private static final Semaphore semaphore = new Semaphore(MAX_CONCURRENT_THREADS);

    public static void main(String[] args) {
        // Create and start multiple threads
        for (int i = 0; i < 10; i++) { // 10 threads will be created
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    try {
                        semaphore.acquire(); // Acquire the semaphore before accessing count
                        count = count + 1; // Critical section
                        semaphore.release(); // Release the semaphore after updating count
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start(); // Start the thread
        }

        // Wait for all threads to finish
        try {
            // Give some time for all threads to complete (a better approach would be to keep track of the threads)
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the final value of count
        System.out.println("Final count: " + count);
    }
}