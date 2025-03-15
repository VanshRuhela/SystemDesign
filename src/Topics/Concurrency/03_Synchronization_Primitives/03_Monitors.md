# Monitors in Multithreading

## Introduction

A **Monitor** is a synchronization construct that allows threads to have both mutual exclusion and the ability to wait (block) for a certain predicate to be met. Monitors help in managing access to shared resources by multiple threads more efficiently and safely.

**Note**: A predicate is any expression that returns true or false

It is an advanced concurrency construct exposed by some programming language frameworks including Java.

#Understanding the problem

## When Mutual Exclusion isn't enough

To understand monitors, let’s first see the problem they solve.

Usually, in multi-threaded applications, a thread needs to wait for some program predicate to be true before it can proceed forward.

Think about a producer/consumer application. If the producer hasn’t produced anything the consumer can’t consume anything, so the consumer must wait on a predicate that lets the consumer know that something has indeed been produced. What could be a crude way of accomplishing this? The consumer could repeatedly check in a loop for the predicate to be set to true. The pattern would resemble the pseudocode below:

    void busyWaitFunction() {
      // acquire mutex
      while (predicate is false){
        // release mutex
       // acquire mutex
    }
      // do something useful
     // release mutex
    }

Within the while loop we’ll first release the mutex giving other threads a chance to acquire it and set the loop predicate to true. And before we check the loop predicate again, we make sure we have acquired the mutex again. This works but is an example of "**spin waiting**" which wastes a lot of CPU cycles.

## Condition Variables

#### How to solve Spin Waiting ?

Continuing on our above problem, we want to test for a predicate with a mutually exclusive lock so that no other thread can change the predicate when we test for it but if we find the predicate to be false, we’d want to wait on a "**condition variable**" instead of spin waiting till the predicate’s value is changed.

**Condition variables** are a synchronization construct used in multithreading to allow threads to wait for certain conditions to occur within the program.

Conceptually, each condition variable exposes two methods **wait()** and **notify()**.

#### wait()

The wait() method when called on the condition variable will cause the associated mutex to be atomically released, and the calling thread would be placed in a **wait queue**.

Note, there could already be other threads in the wait queue that previously invoked wait() on the condition variable. Since the mutex is now released, it gives other threads a chance to change the predicate that will eventually let the thread that was just placed in the wait queue to make progress.

**Example**:
Say we have a consumer thread that checks for the size of the buffer, finds it empty and invokes wait() on a condition variable. The predicate in this example would be the size of the buffer.

#### notify()

**Example Continued**:
Now imagine a producer places an item in the buffer. The predicate, the size of the buffer, just changed and the producer wants to let the consumer threads know that there is an item to be consumed. This producer thread would then invoke notify() on the condition variable.

The notify() method when called on a condition variable causes one of the threads that has been placed in the wait queue to get ready for execution.

Note we didn’t say the woken up thread starts executing, it just gets ready - and that could mean being placed in the **ready queue**. It is only after the producer thread which calls the notify() method has released the associated mutex that the thread in the ready queue starts executing. The thread in the ready queue must wait to acquire the mutex associated with the condition variable before it can start executing.

Lets see how this all translates into code.

    void efficientWaitingFunction() {
      	mutex.acquire() 
      	while (predicate == false) {
        	condVar.wait()
      	}
     	// Do something useful
      	mutex.release()
    }
    
    void changePredicate(){
      mutex.acquire()
      set predicate = true
      condVar.notify()
      mutex.release()
    }


Let’s dry run the above code.

1. Initially, thread A executes efficientWaitingFunction() first, acquires mutex lock and finds the loop predicate is false and enters the loop.

3. Next thread A executes the statement condVar.wait() and is be placed in a wait queue. At the same time thread A gives up the mutex.

5. Now thread B comes along and executes changePredicate() method.

7. Since the mutex was given up by thread A, thread B is able to acquire it and set the predicate to true.

9. Next thread B signals the condition variable condVar.notify(). This step places thread A into the ready queue but thread A doesn’t start executing until thread B has released the mutex.

Note that the order of signaling the condition variable and releasing the mutex can be interchanged, but generally, the preference is to signal first and then release the mutex.

However, the ordering might have ramifications on thread scheduling depending on the threading implementation.

## Why the while Loop
The wary reader would have noticed us using a while loop to test for the predicate. After all, the pseudocode could have been written as follows

    void efficientWaitingFunction({
      	mutex.acquire()
      	if (predicate == false) {
        		condVar.wait()
      	}
      	// Do something useful
      	mutex.release()
    }

If the snippet is re-written in the above manner using an if clause instead of a while then, we need a guarantee that once the variable condVar is signaled, the predicate can’t be changed by any other thread and that the signaled thread becomes the owner of the monitor.

This may not be true. For one, a different thread could get scheduled and change the predicate back to false before the signaled thread gets a chance to execute, therefore the signaled thread must check the predicate again, once it acquires the monitor.

# Technical Definition of Monitors

## How Monitors Work

Monitors encapsulate both the data of the object and the methods that perform operations on that data. Here's how they manage access and synchronization:

### Mutual Exclusion

- **Lock**: Each monitor has an intrinsic lock or mutex, which a thread must acquire before it can execute any of the monitor's methods. This ensures that only one thread can access the monitor's methods at a time, providing mutual exclusion.

### Condition Variables

- **Wait and Notify**: Monitors use condition variables that threads can wait on if a certain condition is not met. For example, if a buffer is empty, a consumer thread might wait until it is signaled (notified) by a producer thread that new data is available.
- **Signal Operations**: Threads within the monitor can signal other threads waiting on the condition variable to wake up, either by notifying one (`notify()`) or all (`notifyAll()`) of the waiting threads.

## Usage and Benefits

### Usage Scenarios

- **Producer-Consumer Problem**: Ensuring that a producer does not add to a full buffer and a consumer does not remove from an empty buffer.
- **Readers-Writers Problem**: Allowing multiple readers or a single writer to access a shared resource without conflicts.

### Benefits

- **Safety**: Monitors prevent race conditions by ensuring mutual exclusion and safe condition checks.
- **Deadlock Prevention**: By managing locks and conditions explicitly, monitors can help avoid deadlocks.
- **Code Simplicity**: Monitors encapsulate synchronization logic, making the code cleaner and easier to maintain.

## Considerations

- **Performance**: While monitors provide safety and simplicity, the overhead of acquiring and releasing a lock can affect performance, especially in highly concurrent applications.
- **Fairness**: The default signaling of monitors may not guarantee fairness; some threads might starve if `notifyAll()` is not used appropriately.