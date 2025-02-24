package com.mknieszner.hazelcastpoc.cache;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class CacheInstance {
    private final HazelcastInstance hazelcastInstance;

    public Map<String, Object> getClusterInfo() {
        return Map.of(
                "members", hazelcastInstance.getCluster().getMembers(),
                "state", hazelcastInstance.getCluster().getClusterState(),
                "version", hazelcastInstance.getCluster().getClusterVersion(),
                "time", hazelcastInstance.getCluster().getClusterTime()
        );
    }

    public IMap<Object, Object> getMap(String cacheName) {
        return hazelcastInstance.getMap(cacheName);
    }

    public void registerCacheUpdateAfterCommit(String key, Object object, String cacheName) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                int retries = 3;
                while (retries > 0) {
                    try {
                        hazelcastInstance.getMap(cacheName).put(key, object);
                        log.info("Cache updated for key = {}", key);
                        break;
                    } catch (Exception e) {
                        retries--;
                        log.warn("Cache update failed for ID = {}, retries left: {}", key, retries);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException ignored) {
                        }
                    }
                }
                if (retries == 0) {
                    log.error("Cache update FAILED for ID = {} after 3 retries!", key);
                }
            }
        });
    }
}
