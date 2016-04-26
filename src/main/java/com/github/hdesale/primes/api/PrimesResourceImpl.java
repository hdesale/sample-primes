package com.github.hdesale.primes.api;

import com.codahale.metrics.Timer;
import com.github.hdesale.primes.service.PrimesCalculationException;
import com.github.hdesale.primes.service.PrimesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.springframework.util.Assert.isTrue;

/**
 * JAX-RS resource implementation to check prime number and get prime numbers in range.
 *
 * @author Hemant
 */
@Component
@Path("/api/v1")
@Produces(MediaType.APPLICATION_JSON)
public class PrimesResourceImpl implements PrimesResource {

    private static final int MAX_VALUE_IN_RANGE = 100000001;

    private static final int RANGE = 501;

    @Autowired
    private PrimesService primesService;

    @Autowired
    private Timer timerForPrimesChecker;

    @Autowired
    private Timer timerForPrimesInRange;

    @GET
    @Path("/primes-checker")
    @Override
    public PrimesCheckerResponse isPrime(@QueryParam("number") String number) {
        Timer.Context context = timerForPrimesChecker.time();
        try {
            assertNotBlank(number, "number");
            int n = parseInt(number, Integer.MAX_VALUE);
            return isPrime(n);
        } catch (IllegalArgumentException | PrimesCalculationException ex) {
            String err = "Failed to check prime number, reason: " + ex.getMessage();
            throw new WebApplicationException(err);
        } finally {
            context.stop();
        }
    }

    @GET
    @Path("/primes-in-range")
    @Override
    public PrimesInRangeResponse getPrimesInRange(@QueryParam("from") String from, @QueryParam("to") String to) {
        Timer.Context context = timerForPrimesInRange.time();
        try {
            assertNotBlank(from, "from");
            assertNotBlank(to, "to");
            int n = parseInt(to, MAX_VALUE_IN_RANGE);
            int m = parseInt(from, n);
            assertValidRange(m, n);
            return getPrimesInRange(m, n);
        } catch (IllegalArgumentException | PrimesCalculationException ex) {
            String err = "Failed to get prime numbers in range, reason: " + ex.getMessage();
            throw new WebApplicationException(err);
        } finally {
            context.stop();
        }
    }

    private PrimesCheckerResponse isPrime(int n) {
        PrimesCheckerResponse response = new PrimesCheckerResponse();
        response.setNumber(n);
        response.setPrime(primesService.isPrime(n));
        return response;
    }

    private PrimesInRangeResponse getPrimesInRange(int from, int to) {
        PrimesInRangeResponse response = new PrimesInRangeResponse();
        response.setFrom(from);
        response.setTo(to);
        int[] primes = primesService.getPrimesInRange(from, to);
        response.setPrimes(primes);
        response.setPrimesCount(primes.length);
        return response;
    }

    private void assertNotBlank(String value, String paramName) {
        isTrue(isNotBlank(value), "Query param '" + paramName + "' can not be blank");
    }

    private int parseInt(String number, int max) {
        int i;
        try {
            i = Integer.parseInt(number);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(number + " is invalid integer");
        }
        isTrue(1 < i, number + " should be > 1");
        isTrue(i < max || max == Integer.MAX_VALUE, number + " should be < " + max);
        return i;
    }

    private void assertValidRange(int from, int to) {
        isTrue(to - from < RANGE, "Difference between 'to' and 'from' should be < " + RANGE);
    }
}
