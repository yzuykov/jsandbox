package ru.yzuykov.jsandbox.application.core.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import ru.yzuykov.jsandbox.application.core.config.CachingConfig;
import ru.yzuykov.jsandbox.application.core.model.dto.EventRq;
import ru.yzuykov.jsandbox.application.core.repository.impl.EventsRepositoryImpl;
import ru.yzuykov.jsandbox.application.core.service.api.MessageService;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Objects.requireNonNull(cacheManager.getCache(CachingConfig.CACHE_MESSAGE)).evict(CachingConfig.CACHE_MESSAGE);
        Objects.requireNonNull(cacheManager.getCache(CachingConfig.CACHE_MESSAGE)).put(CachingConfig.CACHE_MESSAGE, singletonList(EventRq.builder().build()));
        List<EventRq> result2 = messageService.getCachedAllMessages(2);
        List<EventRq> result3 = messageService.getCachedAllMessages(2);
        System.out.println("result0 " + result0);
        System.out.println("result1 " + result1);
        System.out.println("result2 " + result2);
        System.out.println("result3 " + result3);
    }

}