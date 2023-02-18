package ru.yzuykov.jsandbox.application.core.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yzuykov.jsandbox.application.core.component.MessageSubscriber;
import ru.yzuykov.jsandbox.application.core.config.AsyncConfig;
import ru.yzuykov.jsandbox.application.core.service.impl.FlowService;

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