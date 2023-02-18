package ru.yzuykov.jsandbox.application.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@EnableAsync
@Configuration
public class AsyncConfig {

    public static final String NAME_PREFIX = "jsandbox-async-thread-";
    public static final String NAME_SCHEDULED_PREFIX = "jsandbox-scheduled-thread-";

    @Bean("taskExecutor")
    public Executor taskExecutor() {
        return Executors.newCachedThreadPool(new CustomizableThreadFactory(NAME_PREFIX));
    }

    @Bean("scheduledExecutorService")
    public ScheduledExecutorService scheduledExecutorService() {
        return Executors.newScheduledThreadPool(10, new CustomizableThreadFactory(NAME_SCHEDULED_PREFIX));
    }

}
