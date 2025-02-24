package com.mknieszner.hazelcastpoc.cache;

import com.hazelcast.config.AutoDetectionConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("DOCKER")
@Configuration
public class DokcerHazelcastConfig {
    @Bean
    public HazelcastInstance hazelcastInstance(MapConfig productsCacheConfig) {
        Config config = new Config();
        config.setClusterName("products-application-cluster")
                .addMapConfig(productsCacheConfig);

        AutoDetectionConfig autoDetectionConfig = config.getNetworkConfig().getJoin().getAutoDetectionConfig();

        autoDetectionConfig.setEnabled(true);

        return Hazelcast.newHazelcastInstance(config);
    }
}
