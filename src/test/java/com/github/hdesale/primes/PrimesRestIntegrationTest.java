package com.github.hdesale.primes;

import com.github.hdesale.primes.api.PrimesCheckerResponse;
import com.github.hdesale.primes.api.PrimesInRangeResponse;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;

import static org.junit.Assert.*;

/**
 * Integration test for Primes resources.
 *
 * @author Hemant
 */
public class PrimesRestIntegrationTest {

    private static final URI BASE_URI = URI.create("http://localhost:8080");

    private static final String PRIMES_CHECKER = "api/v1/primes-checker";

    private static final String PRIMES_IN_RANGE = "api/v1/primes-in-range";

    private static Client restClient;

    @BeforeClass
    public static void setUp() throws IOException {
        AppMain.start();
        restClient = JerseyClientBuilder.newClient().register(JacksonFeature.class);
    }

    @AfterClass
    public static void tearDown() {
        AppMain.stop();
    }

    private static WebTarget getWebTarget() {
        return restClient.target(BASE_URI);
    }

    @Test
    public void testPrimesChecker() {
        Response response = getWebTarget().path(PRIMES_CHECKER).queryParam("number", 2).request().get();
        assertNotNull(response);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        PrimesCheckerResponse entity = response.readEntity(PrimesCheckerResponse.class);
        assertNotNull(entity);
        assertEquals(2, entity.getNumber());
        assertTrue(entity.isPrime());

        response = getWebTarget().path(PRIMES_CHECKER).queryParam("number", 0).request().get();
        assertNotNull(response);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());

        response = getWebTarget().path(PRIMES_CHECKER).queryParam("number", -100).request().get();
        assertNotNull(response);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());

        response = getWebTarget().path(PRIMES_CHECKER).queryParam("number", "245323027128477").request().get();
        assertNotNull(response);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());

        response = getWebTarget().path(PRIMES_CHECKER).queryParam("number", Integer.MAX_VALUE).request().get();
        assertNotNull(response);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        entity = response.readEntity(PrimesCheckerResponse.class);
        assertNotNull(entity);
        assertEquals(Integer.MAX_VALUE, entity.getNumber());
        assertTrue(entity.isPrime());

        response = getWebTarget().path(PRIMES_CHECKER).queryParam("number", 10283748).request().get();
        assertNotNull(response);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        entity = response.readEntity(PrimesCheckerResponse.class);
        assertNotNull(entity);
        assertEquals(10283748, entity.getNumber());
        assertFalse(entity.isPrime());
    }

    @Test
    public void testPrimesInRange() {
        Response response = getWebTarget().path(PRIMES_IN_RANGE).queryParam("from", 2).queryParam("to", 7).request().get();
        assertNotNull(response);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        PrimesInRangeResponse entity = response.readEntity(PrimesInRangeResponse.class);
        assertNotNull(entity);
        assertEquals(2, entity.getFrom());
        assertEquals(7, entity.getTo());
        assertEquals(4, entity.getPrimesCount());
        assertNotNull(entity.getPrimes());

        response = getWebTarget().path(PRIMES_IN_RANGE).queryParam("from", 123).queryParam("to", 70000).request().get();
        assertNotNull(response);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
}
