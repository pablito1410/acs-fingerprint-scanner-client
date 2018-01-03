package com.pablo.acs.fingerprint.scanner.client.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pablo.acs.fingerprint.scanner.client.infrastructure.rest.RestClientDefault;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public RestClientDefault restClient(final RestTemplate restTemplate, final ObjectMapper objectMapper) {

        return new RestClientDefault(restTemplate, objectMapper);
    }
}
