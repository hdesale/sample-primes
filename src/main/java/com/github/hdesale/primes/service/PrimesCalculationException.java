package com.github.hdesale.primes.service;

/**
 * Exception class to represent any exception raised while doing the prime number calculations.
 *
 * @author Hemant
 */
public class PrimesCalculationException extends RuntimeException {

    public PrimesCalculationException(String message, Throwable cause) {
        super(message, cause);
    }
}
