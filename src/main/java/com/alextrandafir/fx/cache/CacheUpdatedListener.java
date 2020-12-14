package com.alextrandafir.fx.cache;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

@Slf4j
public class CacheUpdatedListener implements CacheEventListener<Object, Object> {

    @Override
    public void onEvent(CacheEvent<?, ?> cacheEvent) {
        log.info("Updated cache", cacheEvent.getKey(), cacheEvent.getOldValue());
    }
}