<!-- TOC -->
* [Thread Synchronization in Java](#thread-synchronization-in-java)
  * [Introduction](#introduction)
      * [There are two synchronizations in Java :](#there-are-two-synchronizations-in-java-)
        * [1. Mutual Exclusive](#1-mutual-exclusive)
        * [2. Cooperation (Inter-thread communication)](#2-cooperation-inter-thread-communication)
  * [Mutual Exclusive](#mutual-exclusive)
      * [Synchronized Methods](#synchronized-methods)
      * [Synchronized Blocks](#synchronized-blocks)
  * [Inter-thread communication](#inter-thread-communication)
      * [wait(), notify(), and notifyAll() Methods](#wait-notify-and-notifyall-methods)
        * [`wait()`](#wait)
        * [`notify()`](#notify)
        * [`notifyAll()`](#notifyall)
  * [Locks](#locks)
        * [1. Locking](#1-locking)
        * [2. Exclusive Access](#2-exclusive-access)
        * [3. Unlocking](#3-unlocking)
      * [Intrinsic Locks (Monitor Locks)](#intrinsic-locks-monitor-locks)
      * [Explicit Locks in java.util.concurrent.locks](#explicit-locks-in-javautilconcurrentlocks)
  * [The 'volatile' keyword](#the-volatile-keyword)
  * [Synchronization Best Practices](#synchronization-best-practices)
<!-- TOC -->
# Thread Synchronization in Java
## Introduction
Thread synchronization in Java ensures that multiple threads can safely access shared resources without causing data inconsistency or other concurrency issues.
It involves coordinating the execution of threads to prevent race conditions and maintain thread safety.

#### There are two synchronizations in Java :
- Process Synchronization
  - Process Synchronization is a technique used to coordinate the execution of multiple processes. It ensures that the shared resources are safe and in order.
- Thread Synchronization
  - Thread Synchronization is used to coordinate and ordering of the execution of the threads in a multi-threaded program.

There are two types of thread synchronization mutual exclusive and inter-thread communication.
##### 1. Mutual Exclusive
- Synchronized method.
- Synchronized block.
- Static synchronization.

##### 2. Cooperation (Inter-thread communication)

## 1) Mutual Exclusive
Mutual Exclusion helps keep threads from interfering with one another while sharing data.


#### Synchronized Blocks
Synchronized blocks allow more fine-grained control over synchronization by specifying the object that should be locked.
Only one thread can execute the synchronized block for a given lock object at a time.
The below block will only be synchronized.
```java
synchronized (lockObject) {
        // Critical section
        }
```
#### Synchronized Methods
A method can be declared as synchronized, ensuring that only one thread can execute it at a time.
When a thread enters a synchronized method, it acquires the **intrinsic lock** (monitor lock) of the object for which the method was invoked.

```java
public synchronized void incrementCounter() {
        counter++;
        }
```
A synchronized instance method in Java is synchronized on the instance (object) owning the method. 
Thus, each instance has its synchronized methods synchronized on a different object: the owning instance.

Only one thread per instance can execute inside a synchronized instance method. 
If more than one instance exist, then one thread at a time **_can execute_** inside a synchronized instance method per instance. 
One thread per instance.

#### Static synchronization
When synchronized keyword is used with a static method, then that is called static synchronization. 
In this, lock will be on the **class** not the object. This means only one thread can access the class at a time.
The purpose of static synchronization is to make the static data thread-safe.

Synchronized static methods are synchronized on the class object of the class the synchronized static method belongs to.
Since only one class object exists in the Java VM per class, only one thread can execute inside a static synchronized method in the **same class.**
In case a class contains more than one static synchronized method, only one thread can execute inside any of these methods at the same time
```java
public static MyStaticCounter{

  private static int count = 0;

  public static synchronized void add(int value){
    count += value;
  }

  public static synchronized void subtract(int value){
    count -= value;
  }
}
```

the same can be archived with **synchronized block** also
eg:
```java
 public class MyClass {

    public static synchronized void log1(String msg1, String msg2){
       log.writeln(msg1);
       log.writeln(msg2);
    }

  
    public static void log2(String msg1, String msg2){
       synchronized(MyClass.class){
          log.writeln(msg1);
          log.writeln(msg2);  
       }
    }
  }
```

##### What will be output of below program where one synchronized method is calling another synchronized ?
Ans: if a synchronized method calls another synchronized method which requires same lock then current thread which is holding the lock can enter into that method without acquiring lock.
eg:
```java
class Demo {
    public synchronized m1(){
        m2();
      System.out.println("inside m1");
    }
    public synchronized m2(){
        m3();
      System.out.println("inside m2");
    }
    public synchronized m3(){
      System.out.println("inside m3");
    }
}
Demo d1 = new Demo();
d1.m1(); // calling the m1
```

## 2) Inter-thread communication
Inter-thread communication in Java allows multiple threads to communicate and coordinate their actions, ensuring proper synchronization.

#### wait(), notify(), and notifyAll() Methods
These methods are used for **inter-thread communication** and must be called from within a synchronized context.

##### wait()
Causes the current thread to wait until another thread invokes notify() or notifyAll() on the same object.
##### notify()
Wakes up a single thread that is waiting on the object's monitor.
##### notifyAll()
Wakes up all threads that are waiting on the object's monitor.

## Locks
Synchronization is built around an internal entity known as the lock or monitor. Locks allow threads to have controlled access to shared resources.

Here's how it works:

##### 1. Locking
When a thread wants to execute a critical section of code, it tries to "lock" the object that contains that code. This is like saying, "I'm using this resource right now, please wait your turn."
##### 2. Exclusive Access
Once a thread has locked the object, no other thread can lock the same object until the first thread is done. This ensures that only one thread can use the resource at a time.
##### 3. Unlocking
When the thread is finished with the critical section, it "unlocks" the object, allowing other threads to lock it and use the resource.


#### Intrinsic Locks (Monitor Locks)
- Intrinsic locks are built-in synchronization mechanisms in Java that are implicitly associated with every object. They are used to synchronize access to an object's methods or blocks of code.
- Intrinsic locks are achieved using `synchronized` keyword on object's methods or code blocks

#### Explicit Locks in java.util.concurrent.locks
- Java provides explicit lock objects for more flexible synchronization, such as `ReentrantLock` , `ReentrantReadWriteLock` and `StampedLock`
- These locks support various features like:
    - Fairness
    - Acquiring lock without blocking
    - Interruption lock acquisition.

## The 'volatile' keyword
In Java, the volatile keyword:

- is used to indicate that a variable's value will be modified by different threads.
- guarantees visibility of changes made to variables across threads.
- any read or write operation on the volatile variable is performed directly on the main memory, ensuring that all threads see the most recent value.

## Synchronization Best Practices
- Minimize the scope of synchronization to reduce contention.
- Prefer using high-level concurrency utilities over low-level synchronization.
- Avoid nested locks and synchronize only the critical section of code.
