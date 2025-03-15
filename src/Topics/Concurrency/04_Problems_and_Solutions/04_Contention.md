# Contention in Multithreading
## Introduction
Contention in the context of multithreading refers to the competition between multiple threads for access to shared resources or locks. It is a critical aspect of concurrent programming that can significantly impact the performance and efficiency of multithreaded applications.

## Causes of Contention
- **Shared Resources:** When multiple threads attempt to access the same resource, such as a shared variable or a critical section protected by a lock.
- **Lock Acquisition:** When multiple threads try to acquire the same lock to enter a synchronized block or method.
- **Limited Resources:** In scenarios where the number of resources is less than the number of threads competing for them.

## Effects of Contention
- **Increased Waiting Time:** Threads may spend more time waiting to acquire locks or access resources, leading to reduced throughput.
- **Reduced Parallelism:** High contention can limit the degree of parallelism, as threads are forced to execute sequentially while waiting for resources.
- **Resource Underutilization:** If threads are frequently blocked, the CPU and other system resources may be underutilized.
- **Deadlocks:** In some cases, contention can lead to deadlocks, where threads are permanently blocked, waiting for each other to release locks.

## Mitigating Contention
- **Lock Granularity:** Use fine-grained locks to reduce the scope of synchronization, allowing more threads to execute concurrently. (Basically apply synchronization to smaller, more specific sections of code)
- **High Level Concurrency Utilities:** Utilize high-level concurrency utilities over low level concurrency primitives. e.g. Concurrent Collections, Thread Pools (Executor Framework in java), Atomic Variables that provide more efficient locking mechanisms.
- **Thread Pooling**: Use thread pools to limit the number of active threads, reducing contention for resources.
- **Lock-Free Algorithms:** Implement lock-free or wait-free algorithms that minimize the use of locks and reduce contention.
- **Optimizing Access Patterns:** Analyze and optimize the access patterns of shared resources to minimize simultaneous access by multiple threads.

