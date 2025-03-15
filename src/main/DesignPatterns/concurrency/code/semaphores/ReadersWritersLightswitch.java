package concurrency.code.semaphores;

import java.util.concurrent.Semaphore;

class Lightswitch {
    private int counter = 0;
    private final Semaphore mutex = new Semaphore(1);

    public void lock(Semaphore semaphore) throws InterruptedException {
        mutex.acquire();                  // Protect the counter update
        counter++;
        if (counter == 1) {               // If this is the first reader, lock the shared resource
            semaphore.acquire();          // Prevent writers from accessing the resource
        }
        mutex.release();                  // Release the mutex for other readers
    }

    public void unlock(Semaphore semaphore) throws InterruptedException {
        mutex.acquire();                  // Protect the counter update
        counter--;
        if (counter == 0) {               // If this is the last reader, unlock the shared resource
            semaphore.release();          // Allow writers to access the resource
        }
        mutex.release();                  // Release the mutex
    }
}

// Reader class
class Reader implements Runnable {
    private final Lightswitch lightswitch;
    private final Semaphore roomEmpty;

    public Reader(Lightswitch lightswitch, Semaphore roomEmpty) {
        this.lightswitch = lightswitch;
        this.roomEmpty = roomEmpty;
    }

    public void run() {
        try {
            lightswitch.lock(roomEmpty);  // Lock the lightswitch for readers
            // Reading (critical section)
            System.out.println(Thread.currentThread().getName() + " is reading...");
            Thread.sleep(1000);           // Simulate reading
            lightswitch.unlock(roomEmpty); // Unlock the lightswitch for readers
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Writer class
class Writer implements Runnable {
    private final Semaphore roomEmpty;

    public Writer(Semaphore roomEmpty) {
        this.roomEmpty = roomEmpty;
    }

    public void run() {
        try {
            roomEmpty.acquire();           // Ensure no readers/writers in the room
            // Writing (critical section)
            System.out.println(Thread.currentThread().getName() + " is writing...");
            Thread.sleep(1000);            // Simulate writing
            roomEmpty.release();           // Release the resource for readers/writers
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ReadersWritersLightswitch {
    public static void main(String[] args) throws InterruptedException {
        Semaphore roomEmpty = new Semaphore(1); // Semaphore to control the shared resource access
        Lightswitch lightswitch = new Lightswitch();

        Thread writer1 = new Thread(new Writer(roomEmpty), "Writer 1");
        Thread reader1 = new Thread(new Reader(lightswitch, roomEmpty), "Reader 1");
        Thread reader2 = new Thread(new Reader(lightswitch, roomEmpty), "Reader 2");
        Thread writer2 = new Thread(new Writer(roomEmpty), "Writer 2");

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
