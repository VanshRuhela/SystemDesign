package org.example.behavioral.nullobjDesign.code;

public class Car implements Vehicle{
    @Override
    public int getTankCap() {
        return 40;
    }

    @Override
    public int getSeatCap() {
        return 2;
    }
}
