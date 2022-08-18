package dev.tpcoder.empfriendly.employee.service;

import dev.tpcoder.empfriendly.employee.model.message.NotificationEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class SendNotificationService implements MessagePublisher<NotificationEvent> {

  private final RedisTemplate<String, NotificationEvent> redisNotificationEventTemplate;
  private final String sentNotificationTopicName;

  public SendNotificationService(RedisTemplate<String, NotificationEvent> redisNotificationEventTemplate,
      @Value("${notification.target.topic}") String sentNotificationTopicName) {
    this.redisNotificationEventTemplate = redisNotificationEventTemplate;
    this.sentNotificationTopicName = sentNotificationTopicName;
  }

  @Override
  public void publish(NotificationEvent notificationEvent) {
    redisNotificationEventTemplate.convertAndSend(sentNotificationTopicName, notificationEvent);
  }
}
