package ru.yzuykov.jsandbox.application.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import ru.yzuykov.jsandbox.application.config.CachingConfig;
import ru.yzuykov.jsandbox.application.model.EventRq;
import ru.yzuykov.jsandbox.application.repository.EventsRepositoryImpl;
import ru.yzuykov.jsandbox.application.service.api.MessageService;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.yzuykov.jsandbox.application.config.CachingConfig.CACHE_MESSAGE;

@SpringBootTest(classes = {CachingConfig.class, MessageServiceImpl.class, EventsRepositoryImpl.class})
class MessageServiceTest {

    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private MessageService messageService;

    @Test
    void text_cacheable() {
        List<EventRq> result0 = messageService.getCachedAllMessages(null);
        List<EventRq> result1 = messageService.getCachedAllMessages(null);
        Objects.requireNonNull(cacheManager.getCache(CACHE_MESSAGE)).evict(CACHE_MESSAGE);
        Objects.requireNonNull(cacheManager.getCache(CACHE_MESSAGE)).put(CACHE_MESSAGE, singletonList(EventRq.builder().build()));
        List<EventRq> result2 = messageService.getCachedAllMessages(2);
        List<EventRq> result3 = messageService.getCachedAllMessages(2);
        System.out.println("result0 " + result0);
        System.out.println("result1 " + result1);
        System.out.println("result2 " + result2);
        System.out.println("result3 " + result3);
    }

}