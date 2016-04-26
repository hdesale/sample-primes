package com.github.hdesale.primes.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test for {@link PrimesInRangeResponse}
 *
 * @author Hemant
 */
public class PrimesInRangeResponseTest {

    private PrimesInRangeResponse response;

    private int[] primes;

    @Before
    public void setUp() throws Exception {
        response = new PrimesInRangeResponse();
        response.setFrom(2);
        response.setTo(7);
        response.setPrimesCount(4);
        response.setPrimes(primes = new int[]{2, 3, 5, 7});
    }

    @Test
    public void testFrom() throws Exception {
        assertNotNull(response);
        assertEquals(2, response.getFrom());
    }

    @Test
    public void testTo() throws Exception {
        assertNotNull(response);
        assertEquals(7, response.getTo());
    }

    @Test
    public void testPrimesCount() throws Exception {
        assertNotNull(response);
        assertEquals(4, response.getPrimesCount());
    }

    @Test
    public void testPrimes() throws Exception {
        assertNotNull(response);
        assertEquals(primes, response.getPrimes());
    }
}