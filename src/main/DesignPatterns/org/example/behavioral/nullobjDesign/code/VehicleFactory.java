package org.example.behavioral.nullobjDesign.code;

public class VehicleFactory {
    static Vehicle getVehicle(String vehicleType){
        if("Car".equals(vehicleType))
            return new Car();

        return new NullVehicle();
    }
}
