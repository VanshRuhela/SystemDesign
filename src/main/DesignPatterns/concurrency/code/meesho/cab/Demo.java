package concurrency.code.meesho.cab;

public class Demo {
    public static void main(String[] args) {
        RideService rideService = new RideService();

        // Add Drivers
        Driver driver1 = new Driver("D1", "John", new Location(12.9716, 77.5946)); // Bangalore
        Driver driver2 = new Driver("D2", "Mike", new Location(12.9352, 77.6245)); // Bangalore
        Driver driver3 = new Driver("D3", "Sam", new Location(28.7041, 77.1025));  // Delhi

        rideService.addDriver(driver1);
        rideService.addDriver(driver2);
        rideService.addDriver(driver3);

        // Add Riders
        Rider rider1 = new Rider("R1", "Alice", new Location(12.9500, 77.5800));  // Bangalore
        Rider rider2 = new Rider("R2", "Bob", new Location(28.7041, 77.1025));   // Delhi

        rideService.addRider(rider1);
        rideService.addRider(rider2);

        // Test case 1: Rider 1 books a ride to another location in Bangalore
        System.out.println("\n=== Test Case 1: Rider 1 Booking Ride ===");
        Location destination1 = new Location(12.9611, 77.6387); // Indiranagar, Bangalore
        Ride ride1 = rideService.bookRide(rider1, destination1);
        if (ride1 != null) {
            System.out.println("Ride booked for Rider 1 with Driver: " + ride1.getDriver().getName());
            System.out.println("Estimated fare: $" + ride1.calculateFare());
        } else {
            System.out.println("No drivers available for Rider 1.");
        }

        // Test case 2: Rider 2 books a ride in Delhi
        System.out.println("\n=== Test Case 2: Rider 2 Booking Ride ===");
        Location destination2 = new Location(28.5355, 77.3910); // Noida, Delhi NCR
        Ride ride2 = rideService.bookRide(rider2, destination2);
        if (ride2 != null) {
            System.out.println("Ride booked for Rider 2 with Driver: " + ride2.getDriver().getName());
            System.out.println("Estimated fare: $" + ride2.calculateFare());
        } else {
            System.out.println("No drivers available for Rider 2.");
        }

        // Test case 3: Complete the ride for Rider 1
        System.out.println("\n=== Test Case 3: Completing Ride for Rider 1 ===");
        if (ride1 != null) {
            double finalFare1 = rideService.completeRide(ride1);
            System.out.println("Ride completed for Rider 1. Final fare: $" + finalFare1);
        }

        // Test case 4: Attempt to book another ride for Rider 1 while the driver is busy
        System.out.println("\n=== Test Case 4: Attempting Another Booking for Rider 1 ===");
        Location destination3 = new Location(12.9716, 77.6413); // Ulsoor, Bangalore
        Ride ride3 = rideService.bookRide(rider1, destination3);
        if (ride3 != null) {
            System.out.println("Ride booked for Rider 1 with Driver: " + ride3.getDriver().getName());
            System.out.println("Estimated fare: $" + ride3.calculateFare());
        } else {
            System.out.println("No drivers available for Rider 1.");
        }
    }
}
