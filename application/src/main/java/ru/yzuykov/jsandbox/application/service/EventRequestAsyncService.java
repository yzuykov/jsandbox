package ru.yzuykov.jsandbox.application.service;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.policy.TimeoutRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.yzuykov.jsandbox.application.model.EventRq;
import ru.yzuykov.jsandbox.application.model.EventRs;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventRequestAsyncService {

    private final EventRequestService eventRequestService;
    private final Retry retry;

    private static final ScheduledExecutorService scheduledExecutorService =
            Executors.newScheduledThreadPool(10);

    @Async
    public CompletableFuture<EventRs> produce(EventRq eventRq) {
        retry
                .getEventPublisher().onRetry(r -> log.info("Retry"));

        return retry
                .executeCompletionStage(scheduledExecutorService, () -> eventRequestService.produce(eventRq))
                .toCompletableFuture();
//
//        TimeoutRetryPolicy timeoutRetryPolicy = new TimeoutRetryPolicy();
//        timeoutRetryPolicy.setTimeout(SECONDS.toMillis(1));
//        retryTemplate.setRetryPolicy(timeoutRetryPolicy);
//        return retryTemplate.execute(context -> {
//            String messageRequest = context.getRetryCount() == 0 ? "message_request" : "retry";
//            log.info("Using topic {}", messageRequest);
//            return eventRequestService.produce(eventRq);
//        });
    }
}
