package ru.yzuykov.jsandbox.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yzuykov.jsandbox.application.component.MessageSubscriber;
import ru.yzuykov.jsandbox.application.config.AsyncConfig;

import static org.mockito.Mockito.spy;

@SpringBootTest(classes = {AsyncConfig.class, MessageSubscriber.class, FlowService.class})
class FlowServiceTest {

    @Autowired
    private FlowService flowService;

    @BeforeEach

    @Test
    void test_execute() {
        flowService.execute();
    }

}