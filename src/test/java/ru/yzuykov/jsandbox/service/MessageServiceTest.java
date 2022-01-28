package ru.yzuykov.jsandbox.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import ru.yzuykov.jsandbox.config.CachingConfig;
import ru.yzuykov.jsandbox.model.EventDTO;
import ru.yzuykov.jsandbox.repository.EventsRepositoryImpl;
import ru.yzuykov.jsandbox.service.api.MessageService;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.yzuykov.jsandbox.config.CachingConfig.CACHE_MESSAGE;

@SpringBootTest(classes = {CachingConfig.class, MessageServiceImpl.class, EventsRepositoryImpl.class})
class MessageServiceTest {

    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private MessageService messageService;

    @Test
    void text_cacheable() {
        List<EventDTO> result0 = messageService.getCachedAllMessages(null);
        List<EventDTO> result1 = messageService.getCachedAllMessages(null);
        Objects.requireNonNull(cacheManager.getCache(CACHE_MESSAGE)).evict(CACHE_MESSAGE);
        Objects.requireNonNull(cacheManager.getCache(CACHE_MESSAGE)).put(CACHE_MESSAGE, singletonList(EventDTO.builder().build()));
        List<EventDTO> result2 = messageService.getCachedAllMessages(2);
        List<EventDTO> result3 = messageService.getCachedAllMessages(2);
        System.out.println("result0 " + result0);
        System.out.println("result1 " + result1);
        System.out.println("result2 " + result2);
        System.out.println("result3 " + result3);
    }

}