package com.github.hdesale.primes.api;

/**
 * Represents the response from the primes in range JAX-RS resource
 * {@link PrimesResourceImpl#getPrimesInRange(String, String)}.<br>
 * <p>
 * This is mutable and not-thread safe class so can not be shared between threads.
 *
 * @author Hemant
 */
public class PrimesInRangeResponse {

    private int from;

    private int to;

    private int primesCount;

    private int[] primes;

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getPrimesCount() {
        return primesCount;
    }

    public void setPrimesCount(int primesCount) {
        this.primesCount = primesCount;
    }

    public int[] getPrimes() {
        return primes;
    }

    public void setPrimes(int[] primes) {
        this.primes = primes;
    }
}
