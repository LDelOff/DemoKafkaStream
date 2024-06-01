package ru.ldeloff.demokafkastream;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class controller {

    private final StreamBridge streamBridge;

    @GetMapping(value = "/test1")
    public String test1() {
        String str = "Produce manual (kafka 1) " + System.currentTimeMillis();
        System.out.println(str);
        streamBridge.send("producer1-out-0", str);
        return "test OK";
    }

    @GetMapping(value = "/test2")
    public String test2() {
        String str = "Produce manual (kafka 2) " + System.currentTimeMillis();
        System.out.println(str);
        streamBridge.send("producer2-out-0", str);
        return "test OK";
    }
}