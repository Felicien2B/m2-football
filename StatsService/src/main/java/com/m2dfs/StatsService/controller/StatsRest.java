package com.m2dfs.StatsService.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class StatsRest {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
