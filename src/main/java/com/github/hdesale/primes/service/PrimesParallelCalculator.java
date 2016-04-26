package com.github.hdesale.primes.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

/**
 * Parallel primes calculator which checks if number is prime
 * using multiple threads to take an advantage of multiple processors.<br>
 * <p>
 * <p>
 * Key points of this algorithm -<br>
 * 1) It is sufficient to use factors only up to sqrt(n)<br>
 * 2) It is sufficient to ignore even factors > 2.
 * </p>
 * <p>
 * This class is thread-safe.
 *
 * @author Hemant
 */
class PrimesParallelCalculator extends AbstractPrimesCalculator {

    private static final int POOL_SIZE = Runtime.getRuntime().availableProcessors();

    PrimesParallelCalculator(int number) {
        super(number);
    }

    @Override
    boolean checkPrime() {
        int sqrt = (int) Math.sqrt(number);
        // using custom pool to avoid parallel stream using ForkJoinPool.commonPool() which may cause contention
        ForkJoinPool forkJoinPool = new ForkJoinPool(POOL_SIZE);
        try {
            return !forkJoinPool.submit(() ->
                    IntStream.range(3, sqrt + 1).parallel()
                            .filter(i -> (i % 2 != 0))
                            .anyMatch(i -> (number % i == 0))).get();
        } catch (InterruptedException | ExecutionException ex) {
            throw new PrimesCalculationException("PrimesParallelCalculator failed to check prime for " + number, ex);
        } finally {
            forkJoinPool.shutdown();
        }
    }
}
