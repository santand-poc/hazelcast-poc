package com.mknieszner.hazelcastpoc.cache;

import com.hazelcast.config.Config;
import com.hazelcast.config.KubernetesConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Profile("KUBERNETES")
@Configuration
@EnableCaching
public class KubernetesHazelcastConfig {
    @Value("${hazelcast.config.namespace:default}")
    private String namespace;
    @Value("${hazelcast.config.discovery.service:hazelcast-service}")
    private String serviceName;

    @Bean
    public HazelcastInstance hazelcastInstance(MapConfig productsCacheConfig) {
        Config config = new Config();
        config.setClusterName("products-application-cluster")
                .addMapConfig(productsCacheConfig);

        KubernetesConfig kubernetesConfig = config.getNetworkConfig().getJoin().getKubernetesConfig();

        kubernetesConfig.setEnabled(true)
                .setProperty("service-name", serviceName)
                .setProperty("namespace", namespace);

        return Hazelcast.newHazelcastInstance(config);
    }
}
