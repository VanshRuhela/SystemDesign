package org.example.behavioral.strategy.strategies;

public class SpecialDriveStrategy implements DriveStrategy{
    @Override
    public void drive() {
        System.out.println("Special Drive");
    }
}
