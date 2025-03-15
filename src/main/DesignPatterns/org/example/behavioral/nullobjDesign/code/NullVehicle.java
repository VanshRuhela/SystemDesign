package org.example.behavioral.nullobjDesign.code;

public class NullVehicle implements Vehicle{
    @Override
    public int getTankCap() {
        return 0;
    }

    @Override
    public int getSeatCap() {
        return 0;
    }
}
