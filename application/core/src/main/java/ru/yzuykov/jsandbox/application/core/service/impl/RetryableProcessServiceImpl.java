package ru.yzuykov.jsandbox.application.core.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import ru.yzuykov.jsandbox.application.core.service.api.RetryableProcessService;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Slf4j
@Service
@RequiredArgsConstructor
public class RetryableProcessServiceImpl implements RetryableProcessService {

    public static final Random RANDOM = new Random();
    private final RetryTemplate retryTemplate;

    @Override
    public void execute(String id) {
        log.info("execute - {}", id);
//        var counter = new AtomicInteger();
//        var result = supplyAsync(() -> callExternal(id, counter.incrementAndGet()))
//                .handle((res, ex) -> isNull(ex) ? res : retryTemplate.execute(ctx -> callExternal(id, counter.incrementAndGet())))
//                .thenApply(res -> {
//                    if ("not_valid".equals(id)) {
//                        throw new IllegalStateException("Valid error");
//                    } else {
//                        return res;
//                    }
//                })
        var result = CompletableFuture.completedFuture(id)
                .thenComposeAsync(i -> getNameAsync(i).thenCombine(getAgeAsync(i), (name, age) -> String.format("Result %s - %d", name, age)))
                .join();
        log.info(result);
    }

    @SneakyThrows
    private String callExternal(String id, Integer count) {
        TimeUnit.SECONDS.sleep(1);
        log.info("callExternal - {} - {}", id, count);
        if (count > 4 || count < 2) {
            return String.format("Call success for %s and call count %d", id, count);
        } else {
            throw new IllegalStateException("Error");
        }
    }


    private CompletableFuture<Integer> getAgeAsync(String id) {
        Executor delayedExecutor = CompletableFuture.delayedExecutor(3L, TimeUnit.SECONDS);
        return CompletableFuture.completedFuture(id)
                .thenApplyAsync(i -> RANDOM.ints(18, 60).findFirst().orElse(18),
                        delayedExecutor);
    }

    private CompletableFuture<String> getNameAsync(String id) {
        Executor delayedExecutor = CompletableFuture.delayedExecutor(5L, TimeUnit.SECONDS);
        return CompletableFuture.completedFuture(id)
                .thenApplyAsync(i -> String.format("name_%s", i),delayedExecutor);
    }
}
