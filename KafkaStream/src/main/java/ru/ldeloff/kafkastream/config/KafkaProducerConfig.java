package ru.ldeloff.kafkastream.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import ru.ldeloff.kafkastream.model.Model;

import java.util.function.Supplier;

@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig {

    private int i = 0;
    private final StreamBridge streamBridge;

    // Периодически отправляет сообщения
    @Bean
    public Supplier<Message<Model>> producerAuto1() {
        return () -> {
            Model message = new Model(++i);
            //        System.out.println("Produce auto 1:" + message);
            return MessageBuilder.withPayload(message)
                    .setHeader(KafkaHeaders.KEY, String.valueOf(message.getId()))
                    .build();
        };
    }

    // Ручная отправка
    public void sendMessage1(Model message) {
        System.out.println("Produce manual (kafka 1): " + message);
        streamBridge.send("producerManual1-out-0",
                MessageBuilder.withPayload(message)
                        .setHeader(KafkaHeaders.KEY, String.valueOf(message.getId()))
                        .build());
    }

    // Для Circuit Breaker
    @Bean
    public Supplier<Message<Model>> producerAutoTestCircuitBreaker1() {
        return () -> {
            Model message = new Model(++i);
            System.out.println("Produce auto (CB) 1:" + message);
            return MessageBuilder.withPayload(message)
                    .setHeader(KafkaHeaders.KEY, String.valueOf(message.getId()))
                    .build();
        };
    }

    /* Кафка 2 */
    @Bean
    public Supplier<Model> producerAuto2() {
        return () -> {
            Model message = new Model(++i);
            //        System.out.println("Produce auto 2:" + message);
            return message;
        };
    }

    public void sendMessage2(Model message) {
        System.out.println("Produce manual (kafka 2): " + message);
        streamBridge.send("producerManual2-out-0", message);
    }
}
