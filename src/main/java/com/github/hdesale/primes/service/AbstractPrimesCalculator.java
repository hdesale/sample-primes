package com.github.hdesale.primes.service;

/**
 * Abstract primes calculator class containing common template of checking a prime number.
 *
 * This is thread-safe class and expects its implementations to be thread-safe as well.
 *
 * @author Hemant
 * @see PrimesSerialCalculator
 * @see PrimesParallelCalculator
 */
abstract class AbstractPrimesCalculator implements PrimesCalculator {

    final int number;

    AbstractPrimesCalculator(int number) {
        this.number = number;
    }

    @Override
    public boolean isPrime() {
        if (number == 2) {
            return true;
        } else if (number <= 1 || number % 2 == 0) {
            return false;
        }
        return checkPrime();
    }

    abstract boolean checkPrime();
}
