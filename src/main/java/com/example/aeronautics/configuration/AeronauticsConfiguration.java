package com.example.aeronautics.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AeronauticsConfiguration {

    @Bean
    public RestTemplate getTemplate (RestTemplateBuilder builder) {
        return builder.build();
    }
}
