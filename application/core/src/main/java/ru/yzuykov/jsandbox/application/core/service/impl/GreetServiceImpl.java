package ru.yzuykov.jsandbox.application.core.service.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.yzuykov.jsandbox.application.core.service.api.GreetService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class GreetServiceImpl implements GreetService {

    @Override
    @SneakyThrows
    public void greet(String name) {
        log.info("Preparing...");
        TimeUnit.SECONDS.sleep(2);
        log.info("Hello " + name);
    }

    @Override
    @Async("taskExecutor")
    public CompletableFuture<Void> greetAsync(String name) {
        return CompletableFuture.runAsync(() -> greet(name));
    }
}
