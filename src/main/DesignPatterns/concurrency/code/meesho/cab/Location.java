package concurrency.code.meesho.cab;

public class Location {
    private double longitude;
    private double latitude;

    public Location(double longitude, double latitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double calculateDistance(Location other){
        double earthRadius = 6371.01; // Earth's radius in kilometers
        double latDiff = Math.toRadians(other.latitude - this.latitude);
        double lonDiff = Math.toRadians(other.longitude - this.longitude);
        double a = Math.sin(latDiff / 2) * Math.sin(latDiff / 2) +
                Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(other.latitude)) *
                        Math.sin(lonDiff / 2) * Math.sin(lonDiff / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadius * c; // Distance in kilometers
    }
}
