package com.github.hdesale.primes.api;

/**
 * JAX-RS resource interface to check prime number and get prime numbers in range.
 *
 * @author Hemant
 */
public interface PrimesResource {

    /**
     * Checks if given integer is prime.
     * This method accepts string instead of an integer to effectively validate and
     * generate appropriate error message.
     *
     * @param number String representing whole integer where 1 < number <= {@link Integer#MAX_VALUE}
     * @return response
     */
    PrimesCheckerResponse isPrime(String number);

    /**
     * Gets all primes in the given range of integers <tt>from</tt> and <tt>to</tt> (both inclusive).
     * This method accepts strings instead of integers to effectively validate and
     * generate appropriate error message.
     *
     * @param from String representing whole integer where <tt>1 < from < to</tt>
     * @param to   String representing whole integer where <tt>from < to < 100,000,001</tt>
     *             and <tt>to-from < 501</tt>
     * @return response
     */
    PrimesInRangeResponse getPrimesInRange(String from, String to);
}
