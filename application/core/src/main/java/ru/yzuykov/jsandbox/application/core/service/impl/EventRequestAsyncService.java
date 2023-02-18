package ru.yzuykov.jsandbox.application.core.service.impl;

import io.github.resilience4j.decorators.Decorators;
import io.github.resilience4j.retry.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.yzuykov.jsandbox.application.core.model.dto.EventRq;
import ru.yzuykov.jsandbox.application.core.model.dto.EventRs;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventRequestAsyncService {

    private final EventRequestService eventRequestService;
    private final Retry retry;
    private final ScheduledExecutorService scheduledExecutorService;

    @Async
    public CompletableFuture<EventRs> produce(EventRq eventRq) {
        retry.getEventPublisher().onRetry(r -> log.info("Retry"));
        return (CompletableFuture<EventRs>) Decorators.ofCompletionStage(() -> eventRequestService.produce(eventRq))
                .withRetry(retry, scheduledExecutorService)
                .get();
    }
}
