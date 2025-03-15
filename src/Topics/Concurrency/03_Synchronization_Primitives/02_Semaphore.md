# Semaphore in Multithreading

## Introduction

A **Semaphore** is a synchronization primitive used in multithreading to control access to shared resources by multiple threads. It helps prevent race conditions by limiting the number of threads that can access a particular resource or a piece of code at the same time.

Think of semaphore as having a limited number of permits to give out. If a semaphore has given out all the permits it has, then any new thread that comes along requesting for a permit will be blocked, till an earlier thread with a permit returns it to the semaphore.

**Example**:

A pool of database connections that can be handed out to requesting threads. Say there are 10 available connections but 50 requesting threads. In such a scenario, a semaphore can only give out 10 permits or connections at any given point in time.

## How Semaphores Work

Semaphores manage concurrent access through the use of a counter which is initialized to a given number representing the number of allowed permits to access the resource. The operations are:

- **Acquire**: When a thread wants to access a resource, it must first acquire a permit from the semaphore. If no permits are available, the thread blocks until one becomes available.
- **Release**: After a thread finishes using the resource, it releases the permit back to the semaphore, increasing the number of available permits.

![semaphore](https://github.com/ad-kmt/java-multi-threading/assets/28861412/658c08da-0f19-4b19-a211-69e241d2646d)


## Types of Semaphores

1. **Binary Semaphore**: Semaphores with a permit count of 1. It only allows one thread to enter the critical section at a time.

   **Note**: Binary Semaphore is not same as Mutex.

2. **Counting Semaphore**: Allows up to `n` permits, enabling multiple threads to access a set of resources up to the maximum limit set.

## Binary Semaphores vs Mutex

A semaphore can potentially act as a mutex if the permits it can give out is set to 1 (Binary Semaphore). However, the most important difference between the two is that:

- In case of a mutex the **same thread** must call acquire and subsequent release on the mutex.
- In case of a binary sempahore, **different threads** can call acquire and release on the semaphore.

This leads us to the concept of **ownership**. A mutex is owned by the thread acquiring it till the point the owning-thread releases it, whereas for a semaphore there’s no notion of ownership.


## Use Cases

Semaphores are particularly useful in scenarios where:

- You need to control access to a pool of resources, such as database connections or thread pools.
- Implementing producer-consumer problems where you need to synchronize producers and consumers accessing a bounded buffer.

## Example Scenario

Imagine a system where you have four threads that need to access a database that only allows two simultaneous connections. You can use a counting semaphore initialized with two permits to manage this access, ensuring no more than two threads connect to the database at the same time.

## Benefits of Using Semaphores

- **Flexibility**: Semaphores can be used to manage access to multiple resources, unlike mutexes which are typically binary.
- **Fairness**: With proper implementation, semaphores can ensure that no thread is starved out from accessing a resource.

## Potential Drawbacks

- **Complexity**: Incorrect handling of acquire and release can lead to deadlocks or resource starvation.
- **Overhead**: Managing semaphore counters and the queue of waiting threads can introduce overhead.

## Conclusion

Semaphores offer a robust method for controlling how multiple threads interact with shared resources. They are versatile and suitable for both simple mutual exclusion and complex synchronization tasks involving multiple resources or conditions.

