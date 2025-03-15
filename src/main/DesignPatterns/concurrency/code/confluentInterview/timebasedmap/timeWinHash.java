package concurrency.code.confluentInterview.timebasedmap;

import java.util.HashMap;

public class timeWinHash {
    private final HashMap<Integer, Integer> hm;
    private final HashMap<Integer, Long> expirationMap;

    private final long ttl;
    public timeWinHash(long ttl){
        this.hm = new HashMap<>();
        this.expirationMap = new HashMap<>();
        this.ttl = ttl;
    }

    public void put(int key , int val){
        System.out.println("putting in "+key+" "+val);
        long currTime = System.currentTimeMillis();
        // check if prev key exists is not expired
        if(hm.containsKey(key)){
            System.out.println("Contains key");
            // its there check for expiry
            long expirationTime = expirationMap.get(key);
            if(currTime > expirationTime){
                System.out.println("expired");
                expirationMap.remove(key);
                hm.remove(key);
                // put it again wih updated one
                hm.put(key, val);
                expirationMap.put(key, System.currentTimeMillis()+ttl);
            }
            else{
                System.out.println("Not expired");
                hm.put(key,val); // updated
                expirationMap.put(key, expirationTime + ttl);
            }
        }
        else{
            System.out.println("New Key");
            hm.put(key, val);
            expirationMap.put(key, currTime+ttl);
        }
    }

    public int get(int key){
        System.out.println("get in "+key);
        long currTime = System.currentTimeMillis();
        if(!hm.containsKey(key)) {
            System.out.println("Does not exist");
            return -1;
        }
        long expirationTime = expirationMap.get(key);
        if(currTime > expirationTime){
            // key is expired
            hm.remove(key);
            expirationMap.remove(key);
            return -1;
        }

        // key is valid
        expirationMap.put(key, expirationTime+ttl);
        return hm.get(key);
    }

    public static void main(String[] args) throws InterruptedException {
        timeWinHash cache = new timeWinHash(3000); // 3 sec ttl
        cache.put(2, 3);
        Thread.sleep(1000);
        cache.put(2, 4);
        Thread.sleep(3000);
        System.out.println(cache.get(2));
        Thread.sleep(6000);
        System.out.println(cache.get(2));
    }
}
