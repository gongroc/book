package org.book.db.redis.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@CacheConfig(cacheNames = "CacheService")
public class CacheService {


    @Cacheable(condition = "#id < 10")
    public long cache(int id) {
        return System.currentTimeMillis();
    }

}
