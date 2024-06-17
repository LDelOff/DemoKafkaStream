package ru.ldeloff.kafkastream.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CircuitBreakerConfig {
    private final CircuitBreakerRegistry circuitBreakerRegistry;

    public CircuitBreakerConfig(CircuitBreakerRegistry circuitBreakerRegistry){
        this.circuitBreakerRegistry = circuitBreakerRegistry;
    }

    public CircuitBreaker circuitBreaker(String cbName) {
        return circuitBreakerRegistry.circuitBreaker(cbName);
    }
}
