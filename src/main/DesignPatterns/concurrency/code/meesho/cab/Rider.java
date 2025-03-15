package concurrency.code.meesho.cab;

public class Rider {
    private String riderId;
    private String name;
    private Location currentLocation;

    public Rider(String r1, String alice, Location location) {
        this.riderId = r1;
        this.name = alice;
        this.currentLocation = location;
    }

    public String getRiderId() {
        return riderId;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void updateLocation(Location location) {
        this.currentLocation = location;
    }
}
