package com.github.hdesale.primes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

/**
 * Primes service implementation.
 * <p>
 * This class is thread-safe.
 *
 * @author Hemant
 */
public class PrimesServiceImpl implements PrimesService {

    private static final int POOL_SIZE = Runtime.getRuntime().availableProcessors();

    private final PrimesCache primesCache;

    public PrimesServiceImpl(PrimesCache primesCache) {
        this.primesCache = primesCache;
    }

    @Override
    public boolean isPrime(int number) {
        Boolean cachedValue = primesCache.get(number);
        if (cachedValue == null) {
            PrimesCalculator calculator = PrimesCalculatorFactory.getPrimesCalculator(number);
            boolean isPrime = calculator.isPrime();
            primesCache.put(number, isPrime);
            return isPrime;
        }
        return cachedValue;
    }

    @Override
    public int[] getPrimesInRange(int from, int to) {
        List<Integer> primes = new ArrayList<>(to - from);
        // using custom pool to avoid parallel stream using ForkJoinPool.commonPool() which may cause contention
        ForkJoinPool forkJoinPool = new ForkJoinPool(POOL_SIZE);
        try {
            forkJoinPool.submit(() ->
                    IntStream.range(from, to + 1)
                            .parallel()
                            .filter(n -> n == 2 || n % 2 != 0)
                            .forEach(n -> {
                                boolean isPrime = isPrime(n);
                                if (isPrime) {
                                    primes.add(n);
                                }
                            })).get();
        } catch (InterruptedException | ExecutionException ex) {
            throw new PrimesCalculationException("Failed to find primes from " + from + " to " + to, ex);
        } finally {
            forkJoinPool.shutdown();
        }
        return primes.stream().sorted().mapToInt(Integer::intValue).toArray();
    }
}
