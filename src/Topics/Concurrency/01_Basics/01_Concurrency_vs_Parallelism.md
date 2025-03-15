# Concurrency vs Parallelism

## Introduction
In software development, particularly in understanding how applications handle multiple tasks, it's crucial to grasp the concepts of concurrency and parallelism. Although they are often used interchangeably, they refer to different types of task processing.

## Simple Explanation

### Concurrency
Concurrency is about dealing with multiple tasks at the same time. It involves making progress on multiple tasks by switching between them. This doesn't necessarily mean that tasks are being completed at the same moment, but rather that multiple tasks are being handled in overlapping periods.

##### Example:
Imagine you are cooking breakfast. You start by putting bread in the toaster. While the bread is toasting, you begin brewing coffee and frying eggs. You are handling cooking eggs, brewing coffee, and toasting bread concurrently, not necessarily at the same time but within the same period.

### Parallelism
Parallelism, on the other hand, refers to performing multiple tasks simultaneously. This requires multiple resources (like CPU cores) so that each task can have its dedicated resource.

##### Example:
Think of a restaurant kitchen where one chef is grilling chicken, another is making salad, and another is preparing a dessert, all at the same time. Each chef is dedicated to one task, and all tasks are truly happening simultaneously.

[![Concurrency and Parallelism](https://i.stack.imgur.com/mUlNV.jpg "Concurrency and Parallelism")](https://stackoverflow.com/questions/1050222/what-is-the-difference-between-concurrency-and-parallelism "Concurrency and Parallelism")

## Technical Definition

### Concurrency
In technical terms, concurrency is a condition in the system where two tasks can start, run, and complete in overlapping time periods. It doesn’t necessarily mean they’ll be running at the same instant. For instance, multitasking on a single-core machine is a form of concurrency.

### Parallelism
Parallelism describes scenarios where many tasks or several parts of a task run at the same time, exploiting multiple processors or cores. A multi-threaded application on a multi-core processor executes multiple threads at the same time, manifesting parallelism.