package dev.tpcoder.empfriendly.location.config;

import dev.tpcoder.empfriendly.location.model.LocationStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

@Configuration
public class RedisStreamConfig {
  private final String streamKey;
  private final StreamListener<String, ObjectRecord<String, LocationStream>> streamListener;

  public RedisStreamConfig(@Value("${location.event.stream}") String streamKey,
      StreamListener<String, ObjectRecord<String, LocationStream>> streamListener) {
    this.streamKey = streamKey;
    this.streamListener = streamListener;
  }

  // Stream purpose
  @Bean
  public Subscription subscription(JedisConnectionFactory jedisConnectionFactory) throws UnknownHostException {
    var options = StreamMessageListenerContainer
        .StreamMessageListenerContainerOptions
        .builder()
        .pollTimeout(Duration.ofSeconds(1))
        .targetType(LocationStream.class)
        .build();
    var listenerContainer = StreamMessageListenerContainer
        .create(jedisConnectionFactory, options);
    var subscription = listenerContainer.receiveAutoAck(
        Consumer.from(streamKey, InetAddress.getLocalHost().getHostName()),
        StreamOffset.create(streamKey, ReadOffset.lastConsumed()),
        streamListener);
    listenerContainer.start();
    return subscription;
  }
}
