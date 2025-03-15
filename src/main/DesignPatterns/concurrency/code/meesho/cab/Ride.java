package concurrency.code.meesho.cab;

public class Ride {
    private String rideId;
    private Rider rider;
    private Driver driver;
    private Location startLocation;
    private Location endLocation;
    private double fare;
    private boolean isCompleted;

    public Ride(String rideId, Rider rider, Driver driver, Location startLocation, Location endLocation) {
        this.rideId = rideId;
        this.rider = rider;
        this.driver = driver;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.isCompleted = false;
    }

    public void completeRide() {
        this.isCompleted = true;
        this.driver.setAvailability(true); // Driver becomes available again
    }

    public double calculateFare() {
        double distance = startLocation.calculateDistance(endLocation);
        this.fare = distance * 10; // Assume $10 per kilometer
        return fare;
    }

    public double getFare() {
        return fare;
    }

    public Driver getDriver(){
        return this.driver;
    }
}
