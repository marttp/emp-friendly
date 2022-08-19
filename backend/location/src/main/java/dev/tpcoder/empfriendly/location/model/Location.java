package dev.tpcoder.empfriendly.location.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;

@Data
@JsonInclude(Include.NON_NULL)
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Location {

  @Id
  @Indexed
  private String id;

  @Indexed
  @NonNull
  private String referenceId;

  @Indexed
  @NonNull
  private String driverId;

  private long timestamp;

  @Indexed
  @NonNull
  private Point geoPoint;
}
