package ru.ldeloff.kafkastream.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.event.CircuitBreakerOnStateTransitionEvent;
import org.springframework.cloud.stream.binding.BindingsLifecycleController;
import org.springframework.cloud.stream.endpoint.BindingsEndpoint;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CircuitBreakerImpl {
    private final BindingsEndpoint bindingsEndpoint;

    protected CircuitBreakerImpl(BindingsEndpoint bindingsEndpoint,
                                 CircuitBreakerConfig circuitBreakerConfig) {
        this.bindingsEndpoint = bindingsEndpoint;
        CircuitBreaker circuitBreaker = circuitBreakerConfig.circuitBreaker("consumerCircuitBreaker");
        circuitBreaker.getEventPublisher().onStateTransition(this::onStateChangeEvent);
    }

    private void onStateChangeEvent(CircuitBreakerOnStateTransitionEvent event) {
        switch (event.getStateTransition().getToState()) {
            case OPEN:
                System.out.println("consumerAutoTestCircuitBreaker1 open");
                bindingsEndpoint.changeState("consumerAutoTestCircuitBreaker1-in-0", BindingsLifecycleController.State.STOPPED);
                break;
            case CLOSED:
                System.out.println("consumerAutoTestCircuitBreaker1 closed");
            case HALF_OPEN:
                System.out.println("consumerAutoTestCircuitBreaker1 half_open");
                bindingsEndpoint.changeState("consumerAutoTestCircuitBreaker1-in-0", BindingsLifecycleController.State.STARTED);
                break;
        }
    }
}