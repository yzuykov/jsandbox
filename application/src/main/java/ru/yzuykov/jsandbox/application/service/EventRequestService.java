package ru.yzuykov.jsandbox.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;
import ru.yzuykov.jsandbox.application.model.EventRq;
import ru.yzuykov.jsandbox.application.model.EventRs;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventRequestService {

    private final KafkaTemplate<String, EventRq> kafkaTemplate;
    private final Map<String, CompletableFuture<EventRs>> resultsMap;

    public CompletableFuture<EventRs> produce(EventRq eventRq) {
        log.info("Using topic {}", "sd");
        return sendToKafka(eventRq, "sd");
    }

    public void produceMono(EventRq eventRq) {
        Mono.fromFuture(() -> sendToKafka(eventRq, "ss"))
                .timeout(Duration.ofMillis(500))
                .retryWhen(getRetryBackoffSpec()
                        .filter(o -> Stream.of(TimeoutException.class).anyMatch(c -> c.isInstance(o))))
                .subscribe(result -> log.info("Result: " + result), ex -> log.error("ex: " + ex));
    }

    private RetryBackoffSpec getRetryBackoffSpec() {
        return Retry.fixedDelay(3, Duration.ofMillis(100));
    }

    @KafkaListener(topics = "message_response", groupId = "123", containerFactory = "kafkaListenerContainerFactory")
    public void consume(EventRs result) {
        ofNullable(resultsMap.remove(result.getId())).ifPresent(f -> f.complete(result));
    }

    private CompletableFuture<EventRs> sendToKafka(EventRq eventRq, String topic) {
        CompletableFuture<EventRs> futureResult = new CompletableFuture<>();
        resultsMap.put(eventRq.getId(), futureResult);
        kafkaTemplate.send(topic, eventRq).addCallback(
                r -> log.info("Result is {}", r),
                futureResult::completeExceptionally
        );
        return futureResult;
    }
}
