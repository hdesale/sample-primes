package com.github.hdesale.primes.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Primes cache to store prime numbers and gain performance improvements in case of repetitive calls.<br>
 * <p>
 * This is singleton cache and it keeps approx max 1000 values in cache.
 * <p>
 * Currently it does not keep weak references to values but it could be enhanced to do so or use any
 * third party lib implementation like Guava or EhCache.
 * <p>
 * This class is thread-safe.
 *
 * @author Hemant
 */
public class PrimesCache {

    private static final PrimesCache singleton = new PrimesCache();

    private static final int MAX_SIZE = 1000;

    private static final int CLEANING_BATCH_SIZE = 100;

    private final ConcurrentHashMap<Integer, Boolean> cache;

    private final AtomicBoolean cleaning;

    private PrimesCache() {
        this.cache = new ConcurrentHashMap<>(MAX_SIZE);
        this.cleaning = new AtomicBoolean(false);
    }

    public static PrimesCache getInstance() {
        return singleton;
    }

    Boolean get(int number) {
        return cache.get(number);
    }

    void put(int number, boolean prime) {
        cache.put(number, prime);
        int size = cache.size();
        if (size > MAX_SIZE) {
            removeExtraEntries(size - MAX_SIZE);
        }
    }

    int size() {
        return cache.size();
    }

    private void removeExtraEntries(int extraSize) {
        // the atomic flag makes sure that only one thread cleans the cache
        if (cleaning.compareAndSet(false, true)) {
            try {
                // get all keys currently available in cache and sort them to only clean small primes
                List<Integer> keys = new ArrayList<>(cache.keySet());
                Collections.sort(keys);
                int size = keys.size();
                extraSize = extraSize > CLEANING_BATCH_SIZE ? CLEANING_BATCH_SIZE : extraSize;
                keys = keys.subList(0, size > extraSize ? extraSize : size);
                keys.forEach(cache::remove);
            } finally {
                cleaning.set(false);
            }
        }
    }
}
