package ru.yzuykov.jsandbox.application.core.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CachingConfig {

    public static final String CACHE_MESSAGE = "message";
    public static final String CACHE_MESSAGE_KEY = "'message'";

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(CACHE_MESSAGE);
    }
}
