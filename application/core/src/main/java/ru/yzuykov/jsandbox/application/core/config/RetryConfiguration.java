package ru.yzuykov.jsandbox.application.core.config;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.support.RetryTemplate;
import ru.yzuykov.jsandbox.application.core.model.dto.EventRs;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

//@EnableRetry
@Configuration
public class RetryConfiguration {

    @Bean
    public RetryTemplate retryTemplate() {
        return RetryTemplate.builder()
                .maxAttempts(2)
                .exponentialBackoff(1000, 2, 5000)
                .build();

    }
    @Bean
    public Retry retry() {
        RetryConfig retryConfig = RetryConfig.custom()
                .maxAttempts(3)
                .retryExceptions(TimeoutException.class)
                .build();
        return  Retry.of("retry", retryConfig);
    }


    @Bean
    public Map<String, CompletableFuture<EventRs>> resultsMap() {
        return new HashMap<>();
    }

}
