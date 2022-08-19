package dev.tpcoder.empfriendly.location.service;

import dev.tpcoder.empfriendly.location.model.LocationStream;
import dev.tpcoder.empfriendly.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReceivedLocationConsumer implements StreamListener<String, ObjectRecord<String, LocationStream>> {
  private final LocationRepository locationRepository;

  @Override
  @SneakyThrows
  public void onMessage(ObjectRecord<String, LocationStream> message) {
    var locationStream = message.getValue();
    log.debug("consumed : {}", locationStream);
    var location = locationStream.getLocation();
    locationRepository.save(location);
    log.debug("save new location for trackingId: {} successful!", locationStream.getTrackingId());
  }
}
