package com.github.hdesale.primes;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.github.hdesale.primes.service.PrimesCache;
import com.github.hdesale.primes.service.PrimesService;
import com.github.hdesale.primes.service.PrimesServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration used to setup Spring container.
 *
 * @author Hemant
 */
@Configuration
@ComponentScan({"com.github.hdesale.primes", "com.github.hdesale.primes.api", "com.github.hdesale.primes.service"})
public class SpringConfiguration {

    @Bean
    public MetricRegistry metricRegistry() {
        MetricRegistry registry = new MetricRegistry();
        JmxReporter.forRegistry(registry).build().start();
        return registry;
    }

    @Bean
    public Timer timerForPrimesChecker(MetricRegistry metricRegistry) {
        return metricRegistry.timer("PrimesChecker");
    }

    @Bean
    public Timer timerForPrimesInRange(MetricRegistry metricRegistry) {
        return metricRegistry.timer("PrimesInRange");
    }

    @Bean
    public PrimesService primesService(PrimesCache primesCache) {
        return new PrimesServiceImpl(primesCache);
    }

    @Bean
    public PrimesCache primesCache() {
        return PrimesCache.getInstance();
    }
}
