## Null Object Design Pattern 

### Problem 
```java
void printVehicleDetails(Vehicle v){
    System.out.println("Seating Cap" + v.getSeatingCap());
    System.out.println("Tank Cap" + v.getTankCap());
}
```
What happens when the object of Vehicle v is null ?  
It will throw Null Pointer Exception (NPE).

### Solution (Not Correct)
```java
void printVehicleDetails(Vehicle v){
    if(v != null) {
        System.out.println("Seating Cap" + v.getSeatingCap());
        System.out.println("Tank Cap" + v.getTankCap());
    }
}
```
### __Note: for how many methods will be put this check?__

Then comes the Null Object Design Pattern in Picture.  

## Null Object Design Pattern
- A null object replaces NULL return type
- No need to put if CHECK for null
- Null obj reflects do Nothing / default behaviour
