package ru.ldeloff.kafkastream.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ldeloff.kafkastream.model.Model;
import ru.ldeloff.kafkastream.service.ModelService;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {
    private final ModelService modelService;

    @Bean
    public Consumer<Model> consumerAuto1() {
        return message -> {
            //        System.out.println("Consume auto 1: " + message);
        };
    }

    @Bean
    public Consumer<Model> consumerManual1() {
        return message -> {
            System.out.println("Consume manual 1: " + message);
        };
    }

    @Bean
    public Consumer<Model> consumerAutoTestCircuitBreaker1() {
        return message -> {
            System.out.println("Consume Auto CB 1: " + message);
            try {
                modelService.sendModel(message);
            } catch (Exception e) {
                System.out.println(e.getClass());
            }
        };
    }

    @Bean
    public Consumer<Model> consumerAuto2() {
        return message -> {
            //        System.out.println("Consume auto 2: " + message);
        };
    }

    @Bean
    public Consumer<Model> consumerManual2() {
        return message -> {
            System.out.println("Consume manual 2: " + message);
        };
    }
}
