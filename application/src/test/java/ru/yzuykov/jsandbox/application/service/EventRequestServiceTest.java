package ru.yzuykov.jsandbox.application.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import reactor.util.retry.Retry;
import ru.yzuykov.jsandbox.application.model.EventRq;
import ru.yzuykov.jsandbox.application.model.EventRs;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.apache.kafka.common.utils.Utils.sleep;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;


@SpringBootTest
//@SpringJUnitConfig(classes = {RetryConfig.class, EventRequestService.class})
class EventRequestServiceTest {


    @MockBean
    private KafkaTemplate<String, EventRq> kafkaTemplate;
    @Autowired
    private EventRequestAsyncService eventRequestAsyncService;
    @Autowired
    private EventRequestService eventRequestService;

    @Test
    void produce() {
        EventRq eventRq = EventRq.builder().id("1123").build();
        CompletableFuture<EventRs> resultFuture = eventRequestAsyncService.produce(eventRq);
        sleep(SECONDS.toMillis(5));
        assertTrue(resultFuture.isCompletedExceptionally());
    }

    @Test
    void produceMono() {
        EventRq eventRq = EventRq.builder().id("1123").build();
        eventRequestService.produceMono(eventRq);
        sleep(SECONDS.toMillis(1));
        eventRequestService.consume(EventRs.builder().id("1123").build());
        verify(kafkaTemplate, atLeast(2)).send(anyString(), eq(eventRq));
    }
}