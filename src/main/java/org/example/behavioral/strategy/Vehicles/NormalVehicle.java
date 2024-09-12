package org.example.behavioral.strategy.Vehicles;

import org.example.behavioral.strategy.strategies.NormalDriveStrategy;

public class NormalVehicle extends Vehicle{
    NormalVehicle(){
        super(new NormalDriveStrategy());
    }
}
