package dev.tpcoder.empfriendly.location.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.tpcoder.empfriendly.location.model.Tracking;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class StartDriveSubscriber implements MessageListener {

  private final ObjectMapper objectMapper;
  private final SupportService supportService;

  public StartDriveSubscriber(ObjectMapper objectMapper, SupportService supportService) {
    this.objectMapper = objectMapper;
    this.supportService = supportService;
  }

  @Override
  public void onMessage(Message message, byte[] pattern) {
    String txtMessage = message.toString();
    Tracking tracking;
    try {
      tracking = objectMapper.readValue(txtMessage, Tracking.class);
    } catch (JsonProcessingException e) {
      log.error("Can't deserialize to Tracking", e);
      throw new RuntimeException(e);
    }
    // TODO: THIS IS JUST A WORKAROUND TO SIMULATE STREAM LOCATION
    Flux.interval(Duration.ofSeconds(3)).take(10)
        .map(aLong -> supportService.publishLocation(tracking.getId(), tracking.getDriverId()))
        .subscribe();
  }
}
