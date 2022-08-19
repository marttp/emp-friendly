package dev.tpcoder.empfriendly.location.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.tpcoder.empfriendly.location.service.StartDriveSubscriber;
import dev.tpcoder.empfriendly.location.service.SupportService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisPubSubConfig {
  private final String startDriveTopicName;
  private final ObjectMapper objectMapper;
  private final SupportService supportService;

  public RedisPubSubConfig(@Value("${location.topic.startdrive}") String startDriveTopicName,
      ObjectMapper objectMapper, SupportService supportService) {
    this.startDriveTopicName = startDriveTopicName;
    this.objectMapper = objectMapper;
    this.supportService = supportService;
  }

  @Bean
  RedisMessageListenerContainer redisContainer(JedisConnectionFactory jedisConnectionFactory) {
    final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(jedisConnectionFactory);
    container.addMessageListener(messageListener(), startDriveChannelTopic());
    return container;
  }

  @Bean
  MessageListenerAdapter messageListener() {
    return new MessageListenerAdapter(new StartDriveSubscriber(objectMapper, supportService));
  }

  @Bean
  ChannelTopic startDriveChannelTopic() {
    return new ChannelTopic(this.startDriveTopicName);
  }
}
