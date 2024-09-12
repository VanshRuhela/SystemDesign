package org.example.behavioral.strategy.Vehicles;

import org.example.behavioral.strategy.strategies.SpecialDriveStrategy;

public class SportsVehicle extends Vehicle{
    public SportsVehicle() {
        super(new SpecialDriveStrategy());
    }
}
