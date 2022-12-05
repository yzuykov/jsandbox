package ru.yzuykov.jsandbox.application.core.service.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.yzuykov.jsandbox.application.core.repository.api.EventsRepository;
import ru.yzuykov.jsandbox.application.core.service.api.MessageService;
import ru.yzuykov.jsandbox.application.core.model.dto.EventRq;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static ru.yzuykov.jsandbox.application.core.config.CachingConfig.CACHE_MESSAGE;
import static ru.yzuykov.jsandbox.application.core.config.CachingConfig.CACHE_MESSAGE_KEY;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    private final EventsRepository eventsRepository;

    @Autowired
    public MessageServiceImpl(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    @SneakyThrows
    public List<EventRq> getAllMessages(Integer age) {
        log.info("Start getAllMessages");
        List<EventRq> result = this.getCachedAllMessages(age);
        log.info("Ready getAllMessages");
        return result;
    }

    @Override
    @SneakyThrows
    @Cacheable(cacheNames = CACHE_MESSAGE, key = CACHE_MESSAGE_KEY)
    public List<EventRq> getCachedAllMessages(Integer age) {
        log.info("Start getCachedAllMessages");
        TimeUnit.SECONDS.sleep(5L);
        log.info("Ready getCachedAllMessages");
        return List.of(
                EventRq.builder().text("One").age(age).build(),
                EventRq.builder().text("Two").age(age).build(),
                EventRq.builder().text("Three").age(age).build()
        );
    }
}
