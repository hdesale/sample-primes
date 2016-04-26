package com.github.hdesale.primes.api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test for {@link PrimesCheckerResponse}
 *
 * @author Hemant
 */
public class PrimesCheckerResponseTest {

    private PrimesCheckerResponse response;

    @Before
    public void setUp() throws Exception {
        response = new PrimesCheckerResponse();
        response.setPrime(true);
        response.setNumber(43);
    }

    @Test
    public void testNumber() throws Exception {
        assertNotNull(response);
        assertEquals(43, response.getNumber());
    }

    @Test
    public void testIsPrime() throws Exception {
        assertNotNull(response);
        assertTrue(response.isPrime());
    }
}