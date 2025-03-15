package concurrency.code.semaphores;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

class ProducerConsumer {
    private LinkedList<Integer> buffer = new LinkedList<>();
    private final int bufferSize;

    // Semaphores to control the buffer
    private final Semaphore empty;  // Counts the number of empty slots
    private final Semaphore full;   // Counts the number of full slots
    private final Semaphore mutex;  // Ensures mutual exclusion

    public ProducerConsumer(int bufferSize) {
        this.bufferSize = bufferSize;
        this.empty = new Semaphore(bufferSize);  // Initially all slots are empty
        this.full = new Semaphore(0);            // Initially no items in the buffer
        this.mutex = new Semaphore(1);           // Mutual exclusion semaphore
    }

    // Producer adds items to the buffer
    public void produce(int item) throws InterruptedException {
        empty.acquire();                         // Decrement empty count (wait if full)
        mutex.acquire();                         // Ensure exclusive access to the buffer

        buffer.add(item);                        // Add the item to the buffer
        System.out.println("Produced: " + item);

        mutex.release();                         // Release the mutex
        full.release();                          // Increment full count (signal consumer)
    }

    // Consumer removes items from the buffer
    public int consume() throws InterruptedException {
        full.acquire();                          // Decrement full count (wait if empty)
        mutex.acquire();                         // Ensure exclusive access to the buffer

        int item = buffer.removeFirst();         // Remove the item from the buffer
        System.out.println("Consumed: " + item);

        mutex.release();                         // Release the mutex
        empty.release();                         // Increment empty count (signal producer)
        return item;
    }

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer(2);  // Buffer of size 5

        // Producer thread
        Thread producer = new Thread(() -> {
            int item = 0;
            try {
                while (true) {
                    pc.produce(item++);
                    Thread.sleep(100); // Simulate some work by the producer
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Consumer thread
        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    pc.consume();
                    Thread.sleep(150); // Simulate some work by the consumer
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
    }
}
