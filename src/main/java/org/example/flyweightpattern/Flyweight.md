# Flyweight Design Pattern

#### Interview Questions
1. Design word processor
2. Design Game

### When to use?
- When memory is limited
- When objects share the data
  - Intrinsic Data : shared among the objects and remains the same one defined one value (here type and body)
  - Extrinsic Data : Changes based on client input and differs from one object to another (here X & Y corr)
- Where creation of objects is expensive in terms of memory.

### Issue
Create an army of Robots say 10L
<div style="font-size: 12px;">

```java
  public class Robots {
    int corX;
    int corY;
    String type;
    Sprits body; // 2d bit map Graphic, heavy object

    Robots(){
      // constuctor
    }
    // getters & setters
  }
  
  public static void main(String args){
    for(int i=0; i++; i<1000000){
      Robot r = new Robot(...);
    }
  }
```
</div>

here the momory used is very high , suppose if the Robot object is 31kb , then 10L robots ~ 31GBs.


### How to solve the issue
- From object (here Robot) remove all the **extrinsic data**  and keep **intrensic data** (this obj is called flyweight obj).
- the __flyweight obj__ can be made immutable (can remove the setters).
- once flyweight obj is created it is **cached** and reused whenever required.


### Read the implementation : for solution of above robot problem