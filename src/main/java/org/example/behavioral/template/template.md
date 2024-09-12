## Template Method Design Pattern 

defines the skeleton of an algorithm in the superclass but lets subclasses override specific steps of the algorithm without changing its structure.


### Where & why to use :
- When we want all the classes to follow specific steps to process the task  
but also 
- Need to provide the flexibility that each class can have their own logic in those specific steps.

### When to use :
The Template Method pattern suggests that you break down an algorithm into a series of steps, turn these steps into methods, and put a series of calls to these methods inside a single template method. The steps may either be abstract, or have some default implementation. To use the algorithm, the client is supposed to provide its own subclass, implement all abstract steps, and override some of the optional ones if needed (but not the template method itself).

