package com.eguller.zpc.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Hazelcast caching configuration.
 *
 * @author eguller
 */
@Configuration
@EnableCaching
public class CachingConfiguration {
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        List<ConcurrentMapCache> cacheTables = new ArrayList<>();
        cacheTables.add(new ConcurrentMapCache("currencies"));
        simpleCacheManager.setCaches(cacheTables);
        return simpleCacheManager;
    }
}
