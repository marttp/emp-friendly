package dev.tpcoder.empfriendly.employee.config;

import dev.tpcoder.empfriendly.employee.model.message.NotificationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {

  @Bean
  public RedisTemplate<String, NotificationEvent> redisNotificationEventTemplate(JedisConnectionFactory jedisConnectionFactory) {
    final RedisTemplate<String, NotificationEvent> template = new RedisTemplate<>();
    template.setConnectionFactory(jedisConnectionFactory);
    RedisSerializer<NotificationEvent> valueSerializer = new Jackson2JsonRedisSerializer<>(NotificationEvent.class);
    template.setValueSerializer(valueSerializer);
    return template;
  }

}
