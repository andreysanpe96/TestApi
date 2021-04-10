package com.service.wolox.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApiConfig {

    @Value("${wolox.api.url}")
    private String apiUrl;

    @Bean
    public RestTemplate apiRestTemplate() {
        return new RestTemplateBuilder().rootUri(apiUrl).build();
    }
}
