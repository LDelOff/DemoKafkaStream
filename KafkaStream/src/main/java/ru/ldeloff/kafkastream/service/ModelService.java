package ru.ldeloff.kafkastream.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import ru.ldeloff.kafkastream.model.Model;

@Service
@RequiredArgsConstructor
public class ModelService {
    private final WebClient webClient;

    @CircuitBreaker(name = "consumerCircuitBreaker")
    @Retry(name = "consumerCircuitBreaker")
    public void sendModel(Model model) {
        System.out.println("Sending Model: " + model);
        Model model1 = webClient.post()
                        .uri("/models")
                        .bodyValue(BodyInserters.fromValue(model))
                        .retrieve()
                .bodyToMono(Model.class).block();
        System.out.println(model1);
    }
}
