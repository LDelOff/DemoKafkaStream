package ru.ldeloff.kafkastream.config;

import org.springframework.cloud.stream.binding.BindingsLifecycleController;
import org.springframework.cloud.stream.binding.InputBindingLifecycle;
import org.springframework.cloud.stream.binding.OutputBindingLifecycle;
import org.springframework.cloud.stream.endpoint.BindingsEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class KafkaStreamConfig {

    @Bean
    public BindingsEndpoint bindingsEndpoint(List<InputBindingLifecycle> inputBindings,
                                             List<OutputBindingLifecycle> outputBindings) {
        return new BindingsEndpoint(new BindingsLifecycleController(inputBindings, outputBindings));
    }
}
