package product;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.ReadFrom;
import io.lettuce.core.SocketOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStaticMasterReplicaConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static io.lettuce.core.ReadFrom.REPLICA_PREFERRED;

public class LettuceConnectionFactoryConfig {

    @Value("${spring.redis.host}")
    String masterHost;

    @Value("${redis.replica.host}")
    String redisReplica;

    @Value("${redis.replica.port}")
    int redisReplicaPort;

    @Value("${spring.redis.port}")
    int port;

    @Value("${spring.redis.password}")
    String password;

    @Value("${spring.redis.ssl}")
    boolean useSSL;

    @Bean
    LettuceConnectionFactory redisConnectionFactory(RedisClusterConfiguration redisConfiguration) {

        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .readFrom(ReadFrom.REPLICA_PREFERRED).build();

        return new LettuceConnectionFactory(redisConfiguration, clientConfig);
    }

    @Bean
    public RedisClusterConfiguration redisClusterConfiguration() {
        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration()
                .clusterNode("redis-cluster----0001-001.redis-cluster---.usw2.cache.amazonaws.com",6379)
                .clusterNode("redis-cluster----0001-002.redis-cluster---.usw2.cache.amazonaws.com",6379);
        clusterConfiguration.setUsername("poc-user");
        clusterConfiguration.setPassword(password);
        return clusterConfiguration;
    }

//    @Bean
//    public RedisStaticMasterReplicaConfiguration redisStaticMasterReplicaConfiguration() {
//        RedisStaticMasterReplicaConfiguration redisStaticMasterReplicaConfiguration =
//                new RedisStaticMasterReplicaConfiguration(masterHost, port);
//        redisStaticMasterReplicaConfiguration.addNode(redisReplica, redisReplicaPort);
//        redisStaticMasterReplicaConfiguration.setPassword(password);
//        return redisStaticMasterReplicaConfiguration;
//    }
//
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory(
//            final RedisStaticMasterReplicaConfiguration redisStaticMasterReplicaConfiguration) {
//        final SocketOptions socketOptions =
//                SocketOptions.builder().connectTimeout(Duration.of(10, ChronoUnit.MINUTES)).build();
//
//        final var clientOptions =
//                ClientOptions.builder().socketOptions(socketOptions).autoReconnect(true).build();
//
//        var clientConfig =
//                LettuceClientConfiguration.builder()
//                        .clientOptions(clientOptions)
//                        .readFrom(REPLICA_PREFERRED);
//
//        if (useSSL) {
//            // aws elasticcache uses in-transit encryption therefore no need for providing certificates
//            clientConfig = clientConfig.useSsl().disablePeerVerification().and();
//        }
//
//        return new LettuceConnectionFactory(
//                redisStaticMasterReplicaConfiguration, clientConfig.build());
//    }

}
