package com.mknieszner.hazelcastpoc.cache;

import com.hazelcast.cluster.MembershipEvent;
import com.hazelcast.cluster.MembershipListener;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.TcpIpConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("DEV")
@Configuration
@Slf4j
public class LocalHazelcastConfig {
    @Bean
    public HazelcastInstance hazelcastInstance(MapConfig productsCacheConfig) {
        Config config = new Config();
        config.setClusterName("products-application-cluster")
                .addMapConfig(productsCacheConfig);

        TcpIpConfig tcpIpConfig = config.getNetworkConfig().getJoin().getTcpIpConfig();

        tcpIpConfig.setEnabled(true)
                .addMember("127.0.0.1:5701")
                .addMember("127.0.0.1:5702");

        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        hazelcastInstance.getCluster().addMembershipListener(new MembershipListener() {
            @Override
            public void memberRemoved(MembershipEvent membershipEvent) {
                log.warn("Hazelcast node {} left the cluster!", membershipEvent.getMember());
            }

            @Override
            public void memberAdded(MembershipEvent membershipEvent) {
                log.info("Hazelcast node {} joined the cluster!", membershipEvent.getMember());
            }
        });

        return hazelcastInstance;
    }
}
