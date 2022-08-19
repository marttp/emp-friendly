package dev.tpcoder.empfriendly.location.config;

import dev.tpcoder.empfriendly.location.model.Tracking;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {

  @Bean
  public RedisTemplate<String, Tracking> redisTrackingTemplate(JedisConnectionFactory jedisConnectionFactory) {
    final RedisTemplate<String, Tracking> template = new RedisTemplate<>();
    template.setConnectionFactory(jedisConnectionFactory);
    RedisSerializer<Tracking> valueSerializer = new Jackson2JsonRedisSerializer<>(Tracking.class);
    template.setValueSerializer(valueSerializer);
    return template;
  }
}
