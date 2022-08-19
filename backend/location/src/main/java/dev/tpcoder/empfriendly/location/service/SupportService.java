package dev.tpcoder.empfriendly.location.service;

import dev.tpcoder.empfriendly.location.model.Location;
import dev.tpcoder.empfriendly.location.model.LocationStream;
import dev.tpcoder.empfriendly.location.model.RequestForDrive;
import dev.tpcoder.empfriendly.location.model.Tracking;
import dev.tpcoder.empfriendly.location.repository.LocationRepository;
import dev.tpcoder.empfriendly.location.repository.TrackingRepository;
import java.time.ZonedDateTime;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class SupportService {

  private final TrackingRepository trackingRepository;
  private final LocationRepository locationRepository;
  private final RedisTemplate<String, Tracking> redisTrackingTemplate;
  private final RedisTemplate<String, String> redisTemplate;
  private final String startDriveTopicName;
  private final String streamLocationKeyName;

  public SupportService(TrackingRepository trackingRepository,
      LocationRepository locationRepository, RedisTemplate<String, Tracking> redisTrackingTemplate,
      RedisTemplate<String, String> redisTemplate,
      @Value("${location.topic.startdrive}") String startDriveTopicName,
      @Value("${location.event.stream}") String streamLocationKeyName) {
    this.trackingRepository = trackingRepository;
    this.locationRepository = locationRepository;
    this.redisTrackingTemplate = redisTrackingTemplate;
    this.redisTemplate = redisTemplate;
    this.startDriveTopicName = startDriveTopicName;
    this.streamLocationKeyName = streamLocationKeyName;
  }

  public Flux<Location> tracking(String trackingId) {
    var trackingData = trackingRepository.findById(trackingId);
    if (trackingData.isEmpty()) {
      throw new RuntimeException("Tracking not found!");
    }
    return Flux.fromStream(locationRepository.findByReferenceId(trackingId).stream());
  }

  public Tracking createTracking(RequestForDrive body) {
    var type = body.type();
    var newTracking = new Tracking();
    newTracking
        .setId(body.referenceId())
        .setType(type.name())
        .setRequesterId(body.requesterId())
        .setDriverId(body.driverId());
    trackingRepository.save(newTracking);
    this.redisTrackingTemplate.convertAndSend(startDriveTopicName, newTracking);
    return newTracking;
  }

  public LocationStream publishLocation(String id, @NonNull String driverId) {
    var point = Utility.randomGeoPoint();
    var loc = new Location(null, id, driverId, ZonedDateTime.now().toInstant().toEpochMilli(), point);
    var locationStream = new LocationStream(id, loc);
    ObjectRecord<String, LocationStream> record = StreamRecords.newRecord()
        .ofObject(locationStream)
        .withStreamKey(streamLocationKeyName);
    this.redisTemplate.opsForStream().add(record);
    return locationStream;
  }

}
