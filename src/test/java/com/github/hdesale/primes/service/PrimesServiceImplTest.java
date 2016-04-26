package com.github.hdesale.primes.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Hemant
 */
public class PrimesServiceImplTest {

    private PrimesService service;

    @Before
    public void setUp() throws Exception {
        service = new PrimesServiceImpl(PrimesCache.getInstance());
    }

    @Test
    public void testPrime() throws Exception {
        assertTrue(service.isPrime(2));
        assertFalse(service.isPrime(1000000));
    }

    @Test
    public void testPrimesInRange() throws Exception {
        int[] primes = service.getPrimesInRange(20, 22);
        assertNotNull(primes);
        assertEquals(0, primes.length);

        primes = service.getPrimesInRange(10000189, 10000588);
        assertNotNull(primes);
        assertEquals(21, primes.length);
    }
}