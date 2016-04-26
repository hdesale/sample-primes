package com.github.hdesale.primes.service;

/**
 * Primes calculator factory to create an instance of calculator depending upon the number.<br>
 * <p>
 * When number is greater than threshold value of sqrt(Integer.MAX_VALUE) factory uses parallel calculator.
 * This threshold is just a guess currently and needs to verified by performance tests or any mathematical
 * calculation.
 * <p>
 * This class is thread-safe.
 *
 * @author Hemant
 */
class PrimesCalculatorFactory {

    private static final int THRESHOLD = (int) Math.sqrt(Integer.MAX_VALUE);

    private PrimesCalculatorFactory() {
        // private constructor to enforce static utilisation of this class
    }

    static PrimesCalculator getPrimesCalculator(int number) {
        if (number > THRESHOLD) {
            return new PrimesParallelCalculator(number);
        }
        return new PrimesSerialCalculator(number);
    }
}
