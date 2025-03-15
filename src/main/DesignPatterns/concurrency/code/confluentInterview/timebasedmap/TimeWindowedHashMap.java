package concurrency.code.confluentInterview.timebasedmap;

import java.util.HashMap;
import java.util.Map;

public class TimeWindowedHashMap<K, V> {
    private final long ttl; // Time-to-live for each key in milliseconds
    private final Map<K, V> map; // Main HashMap to store key-value pairs
    private final Map<K, Long> expirationMap; // To track expiration times for each key

    public TimeWindowedHashMap(long timeToLiveInMillis) {
        this.ttl = timeToLiveInMillis;
        this.map = new HashMap<>();
        this.expirationMap = new HashMap<>();
    }

    // Insert or update the key with the new value
    public void put(K key, V value) {
        long currentTime = System.currentTimeMillis();
        map.put(key, value);
        expirationMap.put(key, currentTime + ttl); // Set new expiration time
    }

    // Get the value associated with the key if not expired
    public V get(K key) {
        long currentTime = System.currentTimeMillis();
        if (!map.containsKey(key)) {
            return null; // Key doesn't exist
        }
        long expirationTime = expirationMap.get(key);
        if (currentTime > expirationTime) {
            // Key has expired, remove from both maps
            map.remove(key);
            expirationMap.remove(key);
            return null; // Or return -1 as per requirement
        }

        // Key is still valid, update the expiration time
        expirationMap.put(key, currentTime + ttl); // Reset expiration time
        return map.get(key); // Return the value
    }

    // Check if the key exists and is still valid (not expired)
    public boolean containsKey(K key) {
        long currentTime = System.currentTimeMillis();
        if (!map.containsKey(key)) {
            return false;
        }
        long expirationTime = expirationMap.get(key);
        if (currentTime > expirationTime) {
            // Key has expired, remove it
            map.remove(key);
            expirationMap.remove(key);
            return false;
        }

        // Key is still valid, so update its expiration
        expirationMap.put(key, currentTime + ttl); // Reset expiration time
        return true; // Key is still valid
    }

    public static void main(String[] args) throws InterruptedException {
        TimeWindowedHashMap<String, String> cache = new TimeWindowedHashMap<>(3000); // 3 seconds TTL

        cache.put("key1", "value1");

        // Before 3 seconds, get should return the value
        System.out.println(cache.get("key1")); // Output: value1

        Thread.sleep(4000); // Wait for 4 seconds, key1 should expire

        // After 4 seconds, get should return null or -1
        System.out.println(cache.get("key1")); // Output: null (-1 if you prefer)

        // Add key1 again, value is reset with new expiration
        cache.put("key1", "value2");
        Thread.sleep(1000);

        System.out.println(cache.get("key1")); // Output: value2
    }
}
