package ru.ldeloff.demokafkastream;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
public class DemoKafkaStreamApplication {

    private int i = 0;

    public static void main(String[] args) {
        SpringApplication.run(DemoKafkaStreamApplication.class, args);
    }

    // Пуляю в кафку 1
    @Bean
    public Consumer<String> consumer1() {
        return s -> System.out.println("Data Consumer 1 (Kafka-1):" + s);
    }

//    // В данном примере продюссер отправляет сообщения периодически
//    @Bean
//    public Supplier<String> producer1() {
//        return () -> {
//            System.out.println("Data Supplier 1 (Kafka-1):" + ++i);
//            return "Data Supplier 1 (Kafka-1):" + i;
//        };
//    }

    // Пуляю в кафку 2
    @Bean
    public Consumer<String> consumer2() {
        return s -> System.out.println("Data Consumer 2 (Kafka-2):" + s);
    }

//    // В данном примере продюссер отправляет сообщения периодически
//    @Bean
//    public Supplier<String> producer2() {
//        return () -> {
//            System.out.println("Data Supplier 2 (Kafka-2):" + ++i);
//            return "Data Supplier 2 (Kafka-2):" + i;
//        };
//    }

}
