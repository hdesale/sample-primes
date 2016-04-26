package com.github.hdesale.primes.api;

/**
 * Represents the response from the primes checker JAX-RS resource {@link PrimesResourceImpl#isPrime(String)}.<br>
 * <p>
 * This is mutable and not-thread safe class so can not be shared between threads.
 *
 * @author Hemant
 */
public class PrimesCheckerResponse {

    private int number;

    private boolean prime;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isPrime() {
        return prime;
    }

    public void setPrime(boolean prime) {
        this.prime = prime;
    }
}
