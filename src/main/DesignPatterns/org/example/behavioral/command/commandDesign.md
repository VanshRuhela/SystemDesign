## Command Design Pattern 

### interview questions :
How to implement the UNDO REDO operations

### Problem
```java
public class Main{
    public static void main(String [] args){
        AirConditioner ac = new AirConditioner();
        ac.turnOnAc();
        ac.setTemp();
        ac.turnOFFAc();
    }
}

public class AirConditioner {
    boolean isOn;
    int temp;
    
    public void turnOnAc(){
        isOn = ture;
    }
    
    public void turnOFFAc(){
        isOn = false;
    }
    
    public void setTemp(int temp){
        this.temp = temp;
    }
}
```
### Problems with the above code
- Lack of Abstraction : the client has to know all the functions , what if in the future there are 
N number of steps to set the temp and there is no simple <u>__setTemp__</u> function
- No Undo / Redo functionality present here. (Also how can we implement that using the above code - **we cannot**)
- How to maintain code , if in future TV remote is also to be added ?

## Command Design Pattern
It separates the logic for :
- Receiver
- Invoker
- Command

Receiver here is AC  
Commands can be turnOnCommand , turnOFFCommand ,etc.  
Invoker => Remote | it can generate many commands 