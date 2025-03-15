package org.example.behavioral.strategy.Vehicles;

import org.example.behavioral.strategy.strategies.DriveStrategy;

public class Vehicle {
    DriveStrategy strategy;

    Vehicle(DriveStrategy strategy){
        this.strategy = strategy;
    }

    public void drive(){
        strategy.drive();
    }
}
