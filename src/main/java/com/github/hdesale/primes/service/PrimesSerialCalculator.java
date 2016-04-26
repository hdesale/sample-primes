package com.github.hdesale.primes.service;

import java.util.stream.IntStream;

/**
 * Primes calculator which checks the prime number in the current
 * thread alone without trying to take advantage of multiple processors.<br>
 * <p>
 * <p>
 * Key points of this algorithm -<br>
 * 1) It is sufficient to use factors only up to sqrt(n)<br>
 * 2) It is sufficient to ignore even factors > 2.
 * </p>
 * <p>
 * This is thread-safe class.
 *
 * @author Hemant
 */
class PrimesSerialCalculator extends AbstractPrimesCalculator {

    PrimesSerialCalculator(int number) {
        super(number);
    }

    @Override
    boolean checkPrime() {
        int sqrt = (int) Math.sqrt(number);
        return !IntStream
                .range(3, sqrt + 1)
                .filter(i -> (i % 2 != 0))
                .anyMatch(i -> (number % i == 0));
    }
}
