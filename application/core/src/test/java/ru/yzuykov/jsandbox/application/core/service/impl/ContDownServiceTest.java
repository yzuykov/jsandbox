package ru.yzuykov.jsandbox.application.core.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yzuykov.jsandbox.application.core.config.AsyncConfig;
import ru.yzuykov.jsandbox.application.core.service.api.ContDownService;
import ru.yzuykov.jsandbox.application.core.service.impl.ContDownServiceImpl;
import ru.yzuykov.jsandbox.application.core.service.impl.GreetServiceImpl;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest(classes = {AsyncConfig.class, ContDownServiceImpl.class, GreetServiceImpl.class})
class ContDownServiceTest {

    @Autowired
    private ContDownService contDownService;

    @Test
    void test_execute() {
        assertDoesNotThrow(() -> contDownService.execute());
    }

    @Test
    void test_executeV2() throws InterruptedException {
        assertDoesNotThrow(() -> contDownService.execute());
        TimeUnit.SECONDS.sleep(5);
    }
}