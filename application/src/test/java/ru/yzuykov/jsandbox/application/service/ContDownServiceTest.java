package ru.yzuykov.jsandbox.application.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yzuykov.jsandbox.application.config.AsyncConfig;
import ru.yzuykov.jsandbox.application.service.api.ContDownService;

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