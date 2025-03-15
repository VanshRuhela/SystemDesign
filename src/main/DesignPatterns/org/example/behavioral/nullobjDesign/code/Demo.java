package org.example.behavioral.nullobjDesign.code;

public class Demo {
    public static void main(String [] args){
        Vehicle vehicle = VehicleFactory.getVehicle("Car");
        printDetails(vehicle);

        vehicle = VehicleFactory.getVehicle("Bike");
        printDetails(vehicle);
    }
    private static void printDetails(Vehicle v){
//        here no need to check if Vehicle is null ==
        System.out.println("Vehicle Seat Cap :" + v.getSeatCap());
        System.out.println("tank cap :"+v.getTankCap());
    }
}
