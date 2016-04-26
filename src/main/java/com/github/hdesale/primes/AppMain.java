package com.github.hdesale.primes;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.net.URI;
import java.util.logging.LogManager;

/**
 * Application main class which starts Grizzly http server along with Jersey and Spring.
 *
 * @author Hemant
 */
public class AppMain {

    private static final Logger log = LoggerFactory.getLogger(AppMain.class);

    private static final String BASE_URI = "http://localhost:8080/";

    private static volatile HttpServer httpServer;

    public static void main(String[] args) {
        registerShutdownHook();
        try {
            start();
            Thread.currentThread().join();
        } catch (IOException ex) {
            log.error("Failed to start Http server, reason: " + ex.getMessage(), ex);
        } catch (InterruptedException ex) {
            log.warn("Interrupted, stopping Http server...");
        } finally {
            stop();
        }
    }

    static void start() throws IOException {
        LogManager.getLogManager().reset();
        HttpServer server = null;
        try {
            server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), getJerseyConfiguration(), false);
            server.start();
        } finally {
            httpServer = server;
        }
        log.info("Http server started at " + BASE_URI);
    }

    static void stop() {
        HttpServer server = httpServer;
        if (server != null) {
            server.shutdownNow();
            log.info("Http server stopped at " + BASE_URI);
        }
    }

    private static JerseyConfiguration getJerseyConfiguration() {
        JerseyConfiguration jerseyConfig = new JerseyConfiguration();
        jerseyConfig.property("contextConfig", new AnnotationConfigApplicationContext(SpringConfiguration.class));
        return jerseyConfig;
    }

    private static void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(AppMain::stop));
    }
}
