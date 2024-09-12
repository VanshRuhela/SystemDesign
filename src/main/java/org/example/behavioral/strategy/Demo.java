package org.example.behavioral.strategy;

import org.example.behavioral.strategy.Vehicles.Vehicle;
import org.example.behavioral.strategy.Vehicles.SportsVehicle;

public class Demo {
    public static void main(String[] args){
        Vehicle vehicle = new SportsVehicle();
        vehicle.drive();

        // now it has the assigned strategy
    }
}
