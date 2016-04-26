package com.github.hdesale.primes.service;

/**
 * Service interface to support prime number calculations.
 *
 * @author Hemant
 */
public interface PrimesService {

    /**
     * Checks if given number is prime.
     *
     * @param number int where 1 < number <= {@link Integer#MAX_VALUE}
     * @return true if <tt>number</tt> is prime
     */
    boolean isPrime(int number);

    /**
     * Gets all primes in range between <tt>m and n</tt> (both inclusive).
     *
     * @param from int where <tt>1 < from < to</tt>
     * @param to int where <tt>from < tom < 100000001 and to-from < 501</tt>
     * @return int array consisting of all primes in a range between <tt>from and to</tt>
     */
    int[] getPrimesInRange(int from, int to);
}
