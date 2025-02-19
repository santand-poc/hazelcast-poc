package com.mknieszner.hazelcastpoc.cache;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionConfig;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizePolicy;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("DOCKER")
@Configuration
@EnableCaching
public class DokcerHazelcastConfig {
    @Bean
    public HazelcastInstance hazelcastInstance(MapConfig productsCacheConfig) {
        Config config = new Config();

        config.setClusterName("products-hazelcast-cluster")
                .addMapConfig(productsCacheConfig);

        config.setManagementCenterConfig(new ManagementCenterConfig()
                .setConsoleEnabled(true)  // Włącz konsolę MC
                .setDataAccessEnabled(true));  // Pozwól na dostęp do danych

        // ✅ Automatyczne wykrywanie pozostałych node
        config.getNetworkConfig()
                .getJoin()
                .getAutoDetectionConfig()
                .setEnabled(true);

        return Hazelcast.newHazelcastInstance(config);
    }

}
