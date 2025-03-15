package concurrency.code.confluentInterview.timebasedmap.avg;

import java.util.*;

public class TimeBasedKeyValueStore {
    private class Entry {
        int value;
        int timestamp;
        Entry(int value, int timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    private final int TIME_WINDOW = 5;  // Time window in seconds
    private Map<String, Entry> map;
    private Deque<String> validKeys;  // Deque to maintain order of valid keys

    public TimeBasedKeyValueStore() {
        map = new HashMap<>();
        validKeys = new LinkedList<>();
    }

    public void put(String key, int value, int currentTime) {
        map.put(key, new Entry(value, currentTime));
        validKeys.addLast(key);
        cleanUpExpiredEntries(currentTime);
    }

    public int get(String key, int currentTime) {
        cleanUpExpiredEntries(currentTime);
        if (!map.containsKey(key)) {
            return -1;  // Key not found or expired
        }
        Entry entry = map.get(key);
        if (isExpired(entry.timestamp, currentTime)) {
            map.remove(key);  // Remove expired key
            return -1;
        }
        return entry.value;
    }

    public double getAverage(int currentTime) {
        cleanUpExpiredEntries(currentTime);

        if (validKeys.isEmpty()) {
            return 0.0;  // No valid entries
        }

        int sum = 0;
        int count = 0;
        for (String key : validKeys) {
            Entry entry = map.get(key);
            sum += entry.value;
            count++;
        }
        return (double) sum / count;
    }

    // Helper method to check if a timestamp is expired
    private boolean isExpired(int timestamp, int currentTime) {
        return currentTime - timestamp > TIME_WINDOW;
    }

    // Helper method to remove expired entries
    private void cleanUpExpiredEntries(int currentTime) {
        while (!validKeys.isEmpty()) {
            String key = validKeys.peekFirst();
            Entry entry = map.get(key);
            if (isExpired(entry.timestamp, currentTime)) {
                validKeys.removeFirst();  // Remove from deque
                map.remove(key);  // Remove from map
            } else {
                break;  // Stop cleaning if no more expired entries
            }
        }
    }

    public static void main(String[] args) {
        TimeBasedKeyValueStore store = new TimeBasedKeyValueStore();
        store.put("foo", 1, 1);
        store.put("bar", 2, 3);
        System.out.println(store.get("foo", 4));  // Output: 1
        System.out.println(store.getAverage(5));  // Output: 1.5
        System.out.println(store.get("foo", 7));  // Output: -1 (expired)
        System.out.println(store.getAverage(8));  // Output: 2 (foo expired, only bar remains)
    }
}