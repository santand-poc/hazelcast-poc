package com.mknieszner.hazelcastpoc.cache;

import com.hazelcast.config.Config;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("DEV")
@Configuration
public class DevHazelcastConfig {
    @Bean
    public HazelcastInstance hazelcastInstance(MapConfig productsCacheConfig) {
        Config config = new Config();

        config.setClusterName("local-hazelcast-cluster");

        config.setManagementCenterConfig(new ManagementCenterConfig()
                .setConsoleEnabled(true)
                .setDataAccessEnabled(true));

        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(true);

        return Hazelcast.newHazelcastInstance(config);
    }

}
