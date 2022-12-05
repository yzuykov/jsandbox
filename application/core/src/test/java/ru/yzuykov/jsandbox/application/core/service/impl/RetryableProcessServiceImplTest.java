package ru.yzuykov.jsandbox.application.core.service.impl;

import io.github.resilience4j.retry.RetryConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import ru.yzuykov.jsandbox.application.core.service.api.RetryableProcessService;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@ContextConfiguration(classes = {RetryConfig.class, RetryableProcessServiceImpl.class} )
class RetryableProcessServiceImplTest {

    @Autowired
    private RetryableProcessService retryableProcessService;

    @Test
    void test_execute() {
        retryableProcessService.execute("not_valid");
    }
}