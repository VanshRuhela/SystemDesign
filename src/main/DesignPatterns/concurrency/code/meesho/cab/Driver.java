package concurrency.code.meesho.cab;

public class Driver {
    private String driverId;
    private String name;
    private Location currentLocation;
    private boolean isAvailable;

    public Driver(String driverId, String name , Location currentLocation){
        this.driverId = driverId;
        this.name = name;
        this.currentLocation = currentLocation;
        this.isAvailable = true;
    }
    public String getDriverId() {
        return driverId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void updateLocation(Location location) {
        this.currentLocation = location;
    }

    public void setAvailability(boolean available) {
        this.isAvailable = available;
    }

    public String getName(){
        return this.name;
    }
}
