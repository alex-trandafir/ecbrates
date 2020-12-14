package com.alextrandafir.fx.cache;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

@Slf4j
public class CacheExpiredListener implements CacheEventListener<Object, Object> {

    @Override
    public void onEvent(CacheEvent<?, ?> cacheEvent) {
        log.info("Expired cache", cacheEvent.getKey(), cacheEvent.getOldValue());
    }
}