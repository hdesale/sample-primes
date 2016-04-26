package com.github.hdesale.primes.service;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Test for {@link PrimesCache}
 *
 * @author Hemant
 */
public class PrimesCacheTest {

    private PrimesCache cache;

    @Before
    public void setUp() {
        cache = PrimesCache.getInstance();
        cache.put(83, true);
    }

    @Test
    public void testInstance() throws Exception {
        assertNotNull(cache);
        assertEquals(cache, PrimesCache.getInstance());
    }

    @Test
    public void testGet() throws Exception {
        Boolean isPrime = cache.get(83);
        assertNotNull(isPrime);
        assertTrue(isPrime);

        isPrime = cache.get(100);
        assertNull(isPrime);
    }

    @Test
    public void testPut() throws Exception {
        cache.put(90, false);

        Boolean isPrime = cache.get(90);
        assertNotNull(isPrime);
        assertFalse(isPrime);
    }

    @Test
    public void testSize() throws Exception {
        for (int i = 0; i < 11000; i++) {
            cache.put(i, RandomUtils.nextBoolean());
        }
        assertTrue(cache.size() != 11000);
    }
}