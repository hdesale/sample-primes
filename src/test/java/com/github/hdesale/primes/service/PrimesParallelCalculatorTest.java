package com.github.hdesale.primes.service;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test for {@link PrimesParallelCalculator}
 *
 * @author Hemant
 */
public class PrimesParallelCalculatorTest {

    @Test
    public void testPrime() throws Exception {
        PrimesCalculator calculator = PrimesCalculatorFactory.getPrimesCalculator(10000189);
        assertNotNull(calculator);
        assertTrue(calculator.isPrime());
    }

    @Test
    public void testNonPrime() throws Exception {
        PrimesCalculator calculator = PrimesCalculatorFactory.getPrimesCalculator(10000588);
        assertNotNull(calculator);
        assertFalse(calculator.isPrime());
    }

    @Test
    public void testZero() throws Exception {
        PrimesCalculator calculator = PrimesCalculatorFactory.getPrimesCalculator(0);
        assertNotNull(calculator);
        assertFalse(calculator.isPrime());
    }

    @Test
    public void testNegative() throws Exception {
        PrimesCalculator calculator = PrimesCalculatorFactory.getPrimesCalculator(-10);
        assertNotNull(calculator);
        assertFalse(calculator.isPrime());
    }

    @Test
    public void testOne() throws Exception {
        PrimesCalculator calculator = PrimesCalculatorFactory.getPrimesCalculator(1);
        assertNotNull(calculator);
        assertFalse(calculator.isPrime());
    }

    @Test
    public void testTwo() throws Exception {
        PrimesCalculator calculator = PrimesCalculatorFactory.getPrimesCalculator(2);
        assertNotNull(calculator);
        assertTrue(calculator.isPrime());
    }
}