package concurrency.code.confluentInterview.timebasedmap.avg;
import java.util.*;

public class OptimizedKeyValueStore {
    private class Entry {
        int value;
        long timestamp;  // Store time in milliseconds
        Entry(int value, long timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    private final int TIME_WINDOW = 5 * 1000;  // 5 seconds in milliseconds
    private Map<String, Entry> keyValueMap;  // Store key-value pairs with timestamps
    private long totalSum;  // Store sum of all valid values
    private int totalCount;  // Store count of valid values

    public OptimizedKeyValueStore() {
        keyValueMap = new HashMap<>();
        totalSum = 0;
        totalCount = 0;
    }

    // Add a key-value pair with the current timestamp
    public void put(String key, int value) {
        long currentTimeMillis = System.currentTimeMillis();  // Get current time in milliseconds

        // If the key already exists, we need to remove its old value from the sum/count
        if (keyValueMap.containsKey(key)) {
            Entry oldEntry = keyValueMap.get(key);
            if (!isExpired(oldEntry.timestamp, currentTimeMillis)) {
                // Remove old value from totalSum and totalCount
                totalSum -= oldEntry.value;
                totalCount--;
            }
        }

        // Add the new key-value pair
        keyValueMap.put(key, new Entry(value, currentTimeMillis));

        // Update totalSum and totalCount
        totalSum += value;
        totalCount++;
    }

    // Retrieve the value for a given key
    public int get(String key) {
        long currentTimeMillis = System.currentTimeMillis();  // Get current time in milliseconds

        if (!keyValueMap.containsKey(key)) {
            return -1;  // Key does not exist
        }

        Entry entry = keyValueMap.get(key);
        if (isExpired(entry.timestamp, currentTimeMillis)) {
            // Key has expired
            keyValueMap.remove(key);
            totalSum -= entry.value;  // Remove the expired value from the totalSum
            totalCount--;  // Decrease the total count of valid values
            return -1;
        }

        return entry.value;  // Key is valid, return its value
    }

    // Calculate the average of all valid values
    public double getAverage() {
        long currentTimeMillis = System.currentTimeMillis(); // Get current time

        // Initialize local sum and count to calculate average from non-expired entries
        int validSum = 0;
        int validCount = 0;

        // Iterate through the key-value map
        Iterator<Map.Entry<String, Entry>> iterator = keyValueMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Entry> entry = iterator.next();
            Entry valueEntry = entry.getValue();

            // Check if the entry is expired
            if (isExpired(valueEntry.timestamp, currentTimeMillis)) {
                // Optionally remove expired entry from the map
                iterator.remove();  // This will clean up expired entries
            } else {
                // Add the non-expired entry to the valid sum and count
                validSum += valueEntry.value;
                validCount++;
            }
        }

        // If there are no valid entries, return -1 (or any other indication that no data is available)
        if (validCount == 0) {
            return -1; // or return 0.0, based on what you prefer
        }

        // Calculate the average based on valid entries
        return (double) validSum / validCount;
    }

    // Helper function to check if a key is expired
    private boolean isExpired(long timestamp, long currentTimeMillis) {
        return (currentTimeMillis - timestamp) > TIME_WINDOW;
    }

    private void cleanUpExpiredEntries(long currentTimeMillis) {
        Iterator<Map.Entry<String, Entry>> iterator = keyValueMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Entry> entry = iterator.next();
            if (isExpired(entry.getValue().timestamp, currentTimeMillis)) {
                iterator.remove(); // Remove expired entry
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        OptimizedKeyValueStore store = new OptimizedKeyValueStore();

        store.put("foo", 1);  // Insert foo with value 1
        Thread.sleep(2000);  // Wait 2 seconds
        store.put("bar", 2);  // Insert bar with value 2

        System.out.println(store.get("foo"));  // Should return 1 (still valid)
        System.out.println(store.getAverage());  // Should return 1.5 (average of 1 and 2)

        Thread.sleep(4000);  // Wait additional 4 seconds (total of 6 seconds)

//        System.out.println(store.get("foo"));  // Should return -1 (foo has expired)
        System.out.println(store.getAverage());  // Should return 2.0 (only bar remains)
    }
}
