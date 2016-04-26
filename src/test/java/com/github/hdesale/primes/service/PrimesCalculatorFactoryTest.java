package com.github.hdesale.primes.service;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test for {@link PrimesCalculatorFactory}
 *
 * @author Hemant
 */
public class PrimesCalculatorFactoryTest {

    @Test
    public void testSerialPrimesCalculator() throws Exception {
        PrimesCalculator calculator = PrimesCalculatorFactory.getPrimesCalculator(100);
        assertNotNull(calculator);
        assertTrue(calculator instanceof PrimesSerialCalculator);
    }

    @Test
    public void testParallelPrimesCalculator() throws Exception {
        PrimesCalculator calculator = PrimesCalculatorFactory.getPrimesCalculator(1000000);
        assertNotNull(calculator);
        assertTrue(calculator instanceof PrimesParallelCalculator);
    }
}