package com.github.hdesale.primes;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Jersey resource configuration used by {@link org.glassfish.grizzly.http.server.HttpServer}.
 *
 * @author Hemant
 */
class JerseyConfiguration extends ResourceConfig {

    JerseyConfiguration() {
        packages("com.github.hdesale.primes");
        register(RequestContextFilter.class);
        register(LoggingFilter.class);
        register(JacksonFeature.class);
        register(AppExceptionMapper.class);
    }

    @Provider
    private static class AppExceptionMapper implements ExceptionMapper<Exception> {

        private static final Logger log = LoggerFactory.getLogger(AppExceptionMapper.class);

        @Override
        public Response toResponse(Exception ex) {
            String error = ex.getMessage();
            log.error(error);
            return createErrorResponse(error);
        }

        private Response createErrorResponse(String error) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .type(MediaType.TEXT_PLAIN)
                    .entity(error)
                    .build();
        }
    }
}
