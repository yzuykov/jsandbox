package ru.yzuykov.jsandbox.application.repository;

import lombok.SneakyThrows;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import ru.yzuykov.jsandbox.application.repository.api.EventsRepository;
import ru.yzuykov.jsandbox.application.model.EventRq;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static ru.yzuykov.jsandbox.application.config.CachingConfig.CACHE_MESSAGE;
import static ru.yzuykov.jsandbox.application.config.CachingConfig.CACHE_MESSAGE_KEY;

@Repository
public class EventsRepositoryImpl implements EventsRepository {

    @Override
    @SneakyThrows
    @Cacheable(cacheNames = CACHE_MESSAGE, key = CACHE_MESSAGE_KEY)
    public List<EventRq> findAll() {
        TimeUnit.SECONDS.sleep(5L);
        return List.of(
                EventRq.builder().text("One").build(),
                EventRq.builder().text("Two").build(),
                EventRq.builder().text("Three").build()
        );
    }
}
