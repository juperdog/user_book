package com.jup.bookorder.configs;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.jup.bookorder.bookorder.securities.UserCredential;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by wasan_kha on 9/8/2018 AD.
 */
@Configuration
public class ApplicationConfiguration {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCache allBooksCache = new CaffeineCache("allBooks",
                Caffeine.newBuilder().maximumSize(100).build());

        CaffeineCache allBooksCacheMap = new CaffeineCache("allBooksMap",
                Caffeine.newBuilder().maximumSize(100).build());

        CaffeineCache recommendedBooksCache = new CaffeineCache("recommendedBooks",
                Caffeine.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES).maximumSize(100).build());

        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(Arrays.asList(allBooksCache, allBooksCacheMap, recommendedBooksCache));
        return manager;
    }

    @Bean
    @Scope(value="request", proxyMode= ScopedProxyMode.TARGET_CLASS)
    public UserCredential userCredential() {
        return new UserCredential();
    }

}
