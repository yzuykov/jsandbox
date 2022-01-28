package ru.yzuykov.jsandbox.repository;

import lombok.SneakyThrows;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import ru.yzuykov.jsandbox.model.EventDTO;
import ru.yzuykov.jsandbox.repository.api.EventsRepository;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static ru.yzuykov.jsandbox.config.CachingConfig.CACHE_MESSAGE;
import static ru.yzuykov.jsandbox.config.CachingConfig.CACHE_MESSAGE_KEY;

@Repository
public class EventsRepositoryImpl implements EventsRepository {

    @Override
    @SneakyThrows
    @Cacheable(cacheNames = CACHE_MESSAGE, key = CACHE_MESSAGE_KEY)
    public List<EventDTO> findAll() {
        TimeUnit.SECONDS.sleep(5L);
        return List.of(
                EventDTO.builder().text("One").build(),
                EventDTO.builder().text("Two").build(),
                EventDTO.builder().text("Three").build()
        );
    }
}
