package concurrency.code.meesho.snl;

public class Random {
    private long seed;
    private static final long multiplier = 0x5DEECE66DL;
    private static final long addend = 0xBL;
    private static final long mask = (1L << 48) - 1;

    public Random() {
        this.seed = System.currentTimeMillis() & mask; // initialize with current time as seed
    }

    public Random(long seed) {
        this.seed = (seed ^ multiplier) & mask;
    }

    // Generates the next random number and updates the seed
    protected int next(int bits) {
        seed = (seed * multiplier + addend) & mask;
        return (int) (seed >>> (48 - bits));
    }

    // Returns a random integer between 0 (inclusive) and the specified bound (exclusive)
    public int nextInt(int bound) {
        if (bound <= 0) {
            throw new IllegalArgumentException("Bound must be positive");
        }
        return next(31) % bound;
    }

    // Returns a random integer between 0 and Integer.MAX_VALUE (inclusive)
    public int nextInt() {
        return next(31);
    }
}

