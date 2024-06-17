package ru.ldeloff.kafkastream.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.binding.BindingsLifecycleController;
import org.springframework.cloud.stream.endpoint.BindingsEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ldeloff.kafkastream.config.KafkaProducerConfig;
import ru.ldeloff.kafkastream.model.Model;

@RestController
@RequiredArgsConstructor
public class Controller {
    int i = 0;
    private final KafkaProducerConfig kafkaProducerConfig;
    private final BindingsEndpoint bindingsEndpoint;

    // Ручная отправка сообщений
    @GetMapping(value = "/test1")
    public String test1() {
        kafkaProducerConfig.sendMessage1(new Model(++i));
        return "test OK, i = " + i;
    }

    @GetMapping(value = "/test2")
    public String test2() {
        kafkaProducerConfig.sendMessage2(new Model(++i));
        return "test OK, i = " + i;
    }

    // Переключатели состояний consumerAutoTestCircuitBreaker1-in-0
    @GetMapping(value = "/test3")
    public String test3() {
        bindingsEndpoint.changeState("consumerAutoTestCircuitBreaker1-in-0", BindingsLifecycleController.State.STOPPED);
        return "test OK";
    }

    @GetMapping(value = "/test4")
    public String test4() {
        bindingsEndpoint.changeState("consumerAutoTestCircuitBreaker1-in-0", BindingsLifecycleController.State.STARTED);
        return "test OK";
    }
}
