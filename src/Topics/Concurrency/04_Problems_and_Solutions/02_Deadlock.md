# Deadlocks
## Introduction
A deadlock is a situation in a multithreading environment where two or more threads are blocked forever, each waiting for the other to release a resource. Deadlocks are a common issue in concurrent programming and can lead to a complete standstill of an application.

## Conditions for Deadlock
Four conditions must be present simultaneously for a deadlock to occur:

- **Mutual Exclusion:** At least one resource must be held in a non-shareable mode, meaning if one thread is using the resource, others must be excluded from using it.
- **Hold and Wait:** A thread must be holding at least one resource and waiting to acquire additional resources that are currently held by other threads.
- **No Preemption:** Resources cannot be forcibly taken away from a thread; a thread must release its resources voluntarily.
- **Circular Wait:** There must be a circular chain of two or more threads, each of which is waiting for a resource held by the next thread in the chain.

#### Example of Deadlock

Consider two threads, T1 and T2, and two resources, R1 and R2:

T1 acquires R1 and needs R2 to proceed.
T2 acquires R2 and needs R1 to proceed.

Both threads are now waiting for each other to release the resource they need, resulting in a deadlock.

## Deadlock Prevention and Avoidance
Several strategies can be used to prevent or avoid deadlocks:

- **Resource Allocation Graph:** Analyze the allocation of resources and dependencies between threads to detect and avoid circular wait conditions.
- **Ordering of Resource Acquisition:** Define a global order for acquiring resources and ensure that all threads acquire resources in this order to prevent circular wait.
- **Timeouts:** Use timeouts for resource acquisition to prevent threads from waiting indefinitely.
- **Try-Lock Mechanisms:** Use try-lock mechanisms that allow a thread to attempt to acquire a resource and proceed without waiting if the resource is unavailable.

## Deadlock Detection and Recovery
In some cases, deadlocks may be allowed to occur, and then detected and resolved:

- **Detection Algorithms:** Use algorithms to periodically check for the presence of a deadlock in the system.
- **Recovery Methods:** Once a deadlock is detected, use methods such as resource preemption, thread rollback, or thread termination to recover from the deadlock.
