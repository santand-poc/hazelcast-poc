package com.mknieszner.hazelcastpoc.cache;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionConfig;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.KubernetesConfig;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizePolicy;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Profile("OPENSHIFT")
@Configuration
@EnableCaching
public class OpenshiftHazelcastConfig {
    @Bean
    public HazelcastInstance hazelcastInstance() {
        // ✅ Konfiguracja mapy cache
        MapConfig productsCacheConfig = new MapConfig("productsCache")
                .setMaxIdleSeconds(300)
                .setTimeToLiveSeconds(1200)
                .setBackupCount(1)
                .setEvictionConfig(new EvictionConfig()
                        .setEvictionPolicy(EvictionPolicy.LRU)
                        .setMaxSizePolicy(MaxSizePolicy.USED_HEAP_SIZE)
                        .setSize(10)); // Policy based on maximum used JVM heap memory in !!!megabytes!!! per data structure (map, cache etc) on each Hazelcast instance.

        // ✅ Konfiguracja Hazelcast
        Config config = new Config();
        config.setClusterName("products-hazelcast-cluster")
                .addMapConfig(productsCacheConfig);

        // ✅ Management Center (opcjonalnie)
        config.setManagementCenterConfig(new ManagementCenterConfig()
                .setConsoleEnabled(true)
                .setDataAccessEnabled(true));

        KubernetesConfig kubernetesConfig = new KubernetesConfig();
        kubernetesConfig.setEnabled(true);
        kubernetesConfig.setProperty("service-name", "hazelcast-service"); // ✅ Używamy `service-name`
        kubernetesConfig.setProperty("namespace", "mateusz-dev"); // ✅ Upewniamy się, że Hazelcast szuka w `default` - openshift

        JoinConfig joinConfig = config.getNetworkConfig().getJoin();
        joinConfig.setKubernetesConfig(kubernetesConfig); // 🔥 Kluczowe! Ustawiamy konfigurację Discovery poprawnie.

        return Hazelcast.newHazelcastInstance(config);
    }
}
