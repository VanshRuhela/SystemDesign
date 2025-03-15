package org.example.behavioral.interpreter.code;

import java.util.HashMap;
import java.util.Map;

public class Context {
    Map<String , Integer> contextMap = new HashMap<>();
    public void put(String variable , int intVal){
        contextMap.put(variable,intVal);
    }

    public int get(String variable){
        return contextMap.get(variable);
    }
}
