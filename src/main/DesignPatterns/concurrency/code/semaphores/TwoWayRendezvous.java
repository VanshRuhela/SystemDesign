package concurrency.code.semaphores;
import java.util.concurrent.Semaphore;

public class TwoWayRendezvous {
    // Semaphores to control the order of events
    private static final Semaphore semaphoreA = new Semaphore(0); // For synchronizing b1 -> a2
    private static final Semaphore semaphoreB = new Semaphore(0); // For synchronizing a1 -> b2

    public static void main(String[] args) {
        // Thread A
        Thread threadA = new Thread(() -> {
            try {
                // a1 happens before b2
                System.out.println("Thread A: Executing a1");

                // Signal Thread B that a1 has completed
                semaphoreB.release(); // a1 completes, allow b2 to proceed

                // Wait for b1 to complete before executing a2
                semaphoreA.acquire();
                System.out.println("Thread A: Executing a2 after b1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Thread B
        Thread threadB = new Thread(() -> {
            try {
                // b1 happens before a2
                System.out.println("Thread B: Executing b1");

                // Signal Thread A that b1 has completed
                semaphoreA.release(); // b1 completes, allow a2 to proceed

                // Wait for a1 to complete before executing b2
                semaphoreB.acquire();
                System.out.println("Thread B: Executing b2 after a1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Start both threads
        threadA.start();
        threadB.start();

        // Wait for both threads to finish
//        try {
//            threadA.join();
//            threadB.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}