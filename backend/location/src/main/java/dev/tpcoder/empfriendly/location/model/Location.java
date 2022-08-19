package dev.tpcoder.empfriendly.location.model;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import lombok.NonNull;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;

@Value
@Document("location")
public class Location {

  @Id
  @Indexed
  String id;

  @Indexed
  @NonNull
  String referenceId;

  @Indexed
  @NonNull
  String driverId;

  long timestamp;

  @Indexed
  @NonNull
  Point geoPoint;
}
