package dev.tpcoder.empfriendly.shipment.config;

import dev.tpcoder.empfriendly.shipment.ShipmentSubscriber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisConfig {

  private final String shipmentTopicName;

  public RedisConfig(
      @Value("${subscribe.topic.shipment}") String shipmentTopicName) {
    this.shipmentTopicName = shipmentTopicName;
  }

  @Bean
  RedisMessageListenerContainer redisContainer(JedisConnectionFactory jedisConnectionFactory) {
    final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(jedisConnectionFactory);
    container.addMessageListener(messageListener(), shipmentChannelTopic());
    return container;
  }

  @Bean
  MessageListenerAdapter messageListener() {
    return new MessageListenerAdapter(new ShipmentSubscriber());
  }

  @Bean
  ChannelTopic shipmentChannelTopic() {
    return new ChannelTopic(this.shipmentTopicName);
  }
}
