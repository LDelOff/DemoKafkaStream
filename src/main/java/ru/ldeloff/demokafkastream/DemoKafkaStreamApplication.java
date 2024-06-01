package ru.ldeloff.demokafkastream;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.apache.kafka.common.network.Mode;
import org.apache.logging.log4j.CloseableThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.DataInput;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

@RestController
@SpringBootApplication
public class DemoKafkaStreamApplication {

    private int i = 0;

    @Autowired
    private StreamBridge streamBridge;

    public static void main(String[] args) {
        SpringApplication.run(DemoKafkaStreamApplication.class, args);
    }

    /* Кафка 1 */
    // Консьюмер
    @Bean
    public Consumer<Model> consumerAuto1() {
        return message -> {;
    //        System.out.println("Consume auto 1: " + message);
        };
    }

    // Продюсер (сам периодически отправляет сообщения)
    @Bean
    public Supplier<Model> producerAuto1() {
        return () -> {
            Model message = new Model(++i);
    //        System.out.println("Produce auto 1:" + message);
            return message;
        };
    }



    // Вручную отправим сообщение
    @GetMapping(value = "/test1")
    public String test1() {
        Model message = new Model(++i);
        System.out.println("Produce manual (kafka 1): " + message);
        streamBridge.send("producerManual1-out-0", message);
        return "test OK";
    }

    // Консьюмер
    @Bean
    public Consumer<Model> consumerManual1() {
        return message -> {
            System.out.println("Consume manual 1: " + message);
        };
    }


    /* Кафка 2 */
    // Продюсер (сам периодически отправляет сообщения)
    @Bean
    public Supplier<Model> producerAuto2() {
        return () -> {
            Model message = new Model(++i);
    //        System.out.println("Produce auto 2:" + message);
            return message;
        };
    }

    // Консьюмер
    @Bean
    public Consumer<Model> consumerAuto2() {
        return message -> {
    //        System.out.println("Consume auto 2: " + message);
        };
    }

    // Вручную отправим сообщение
    @GetMapping(value = "/test2")
    public String test2() {
        Model message = new Model(++i);
        System.out.println("Produce manual (kafka 2): " + message);
        streamBridge.send("producerManual2-out-0", message);
        return "test OK";
    }

    // Консьюмер
    @Bean
    public Consumer<Model> consumerManual2() {
        return message -> {
            System.out.println("Consume manual 2: " + message);
        };
    }
}
