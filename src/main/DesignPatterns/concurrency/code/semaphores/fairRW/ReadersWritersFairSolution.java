package concurrency.code.semaphores.fairRW;

import java.util.concurrent.Semaphore;

// Lightswitch class to manage multiple readers
class Lightswitch {
    private int counter = 0;
    private final Semaphore mutex = new Semaphore(1);

    public void lock(Semaphore semaphore) throws InterruptedException {
        mutex.acquire();                  // Protect the counter update
        counter++;
        if (counter == 1) {               // First reader locks the resource
            semaphore.acquire();          // Prevent writers from accessing the resource
        }
        mutex.release();                  // Release mutex for other readers
    }

    public void unlock(Semaphore semaphore) throws InterruptedException {
        mutex.acquire();                  // Protect the counter update
        counter--;
        if (counter == 0) {               // Last reader unlocks the resource
            semaphore.release();          // Allow writers to access the resource
        }
        mutex.release();                  // Release mutex
    }
}

// Reader class
class Reader implements Runnable {
    private final Lightswitch lightswitch;
    private final Semaphore roomEmpty;
    private final Semaphore turnstile;

    public Reader(Lightswitch lightswitch, Semaphore roomEmpty, Semaphore turnstile) {
        this.lightswitch = lightswitch;
        this.roomEmpty = roomEmpty;
        this.turnstile = turnstile;
    }

    public void run() {
        try {
            turnstile.acquire();          // Wait for turnstile to be available (allow writers to go first)
            turnstile.release();          // Immediately release, allowing subsequent readers to enter
            lightswitch.lock(roomEmpty);  // Lightswitch for readers
            // Reading (critical section)
            System.out.println(Thread.currentThread().getName() + " is reading...");
            Thread.sleep(1000);           // Simulate reading
            lightswitch.unlock(roomEmpty); // Lightswitch for readers
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Writer class
class Writer implements Runnable {
    private final Semaphore roomEmpty;
    private final Semaphore turnstile;

    public Writer(Semaphore roomEmpty, Semaphore turnstile) {
        this.roomEmpty = roomEmpty;
        this.turnstile = turnstile;
    }

    public void run() {
        try {
            turnstile.acquire();           // Block readers from entering
            roomEmpty.acquire();           // Ensure no readers/writers are in the room
            // Writing (critical section)
            System.out.println(Thread.currentThread().getName() + " is writing...");
            Thread.sleep(1000);            // Simulate writing
            roomEmpty.release();           // Release the room for readers/writers
            turnstile.release();           // Allow readers to enter
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ReadersWritersFairSolution {
    public static void main(String[] args) throws InterruptedException {
        Semaphore roomEmpty = new Semaphore(1);   // Semaphore to control access to the shared resource
        Semaphore turnstile = new Semaphore(1);   // Turnstile to prioritize writers
        Lightswitch lightswitch = new Lightswitch();

        // Create readers and writers
        Thread writer1 = new Thread(new Writer(roomEmpty, turnstile), "Writer 1");
        Thread reader1 = new Thread(new Reader(lightswitch, roomEmpty, turnstile), "Reader 1");
        Thread reader2 = new Thread(new Reader(lightswitch, roomEmpty, turnstile), "Reader 2");
        Thread writer2 = new Thread(new Writer(roomEmpty, turnstile), "Writer 2");

        // Start the readers and writers
        writer1.start();
        reader1.start();
        reader2.start();
        writer2.start();

        writer1.join();
        reader1.join();
        reader2.join();
        writer2.join();
    }
}