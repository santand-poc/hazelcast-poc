package com.mknieszner.hazelcastpoc.cache;


import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.KubernetesConfig;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("KUBERNETES")
@Configuration
@EnableCaching
public class KubernetesHazelcastConfig {

    @Bean
    public HazelcastInstance hazelcastInstance(MapConfig productsCacheConfig) {
        Config config = new Config();
        config.setClusterName("products-hazelcast-cluster")
                .addMapConfig(productsCacheConfig);

        config.setManagementCenterConfig(new ManagementCenterConfig()
                .setConsoleEnabled(true)
                .setDataAccessEnabled(true));

        KubernetesConfig kubernetesConfig = new KubernetesConfig();
        kubernetesConfig.setEnabled(true);
        kubernetesConfig.setProperty("service-name", "hazelcast-service");
        kubernetesConfig.setProperty("namespace", "default"); // ✅ Hazelcast szuka w `default` - minikube

        JoinConfig joinConfig = config.getNetworkConfig().getJoin();
        joinConfig.setKubernetesConfig(kubernetesConfig);

        return Hazelcast.newHazelcastInstance(config);
    }
}
