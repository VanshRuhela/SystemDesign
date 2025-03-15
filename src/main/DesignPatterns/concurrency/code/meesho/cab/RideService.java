package concurrency.code.meesho.cab;

import java.util.*;

public class RideService {
    private Map<String , Driver> drivers;
    private Map<String, Rider> riders;
    private List<Ride> activeRides;

    public RideService(){
        drivers = new HashMap<>();
        riders = new HashMap<>();
        activeRides = new ArrayList<>();
    }

    // Add Driver
    public void addDriver(Driver driver){
        drivers.put(driver.getDriverId(), driver);
    }

    public void addRider(Rider rider){
        riders.put(rider.getRiderId(), rider);
    }

    public void updateDriversLocation(String driverId , Location location){
        Driver driver = drivers.get(driverId);
        if(driver!=null)
            driver.updateLocation(location);
    }

    public void updateRiderLocation(String riderId , Location location){
        Rider rider = riders.get(riderId);
        rider.updateLocation(location);
    }

    public Driver findRide(Rider rider){
        Driver nearestDriver = null;
        double minDis = Double.MAX_VALUE;
        for(Driver driver : drivers.values()){
            if(driver.isAvailable()){
                double distance = driver.getCurrentLocation().calculateDistance(rider.getCurrentLocation());
                if(distance < minDis){
                    minDis = distance;
                    nearestDriver = driver;
                }
            }
        }
        return nearestDriver;
    }

    public Ride bookRide(Rider rider, Location destination){
        Driver driver = findRide(rider);
        driver.setAvailability(false);
        Ride ride = new Ride(UUID.randomUUID().toString(), rider , driver, rider.getCurrentLocation(),destination);
        activeRides.add(ride);
        return ride;
    }

    public double completeRide(Ride ride){
        ride.completeRide();
        return ride.calculateFare();
    }
}
