package ru.yzuykov.jsandbox.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@EnableAsync
@Configuration
public class AsyncConfig {

    public static final String NAME_PREFIX = "jsandbox-async-thread-";

    @Bean("taskExecutor")
    public Executor taskExecutor() {
        return Executors.newCachedThreadPool(new CustomizableThreadFactory(NAME_PREFIX));
    }
}
