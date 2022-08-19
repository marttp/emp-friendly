package dev.tpcoder.empfriendly.location.model;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Tracking {

  @Id
  @Indexed
  private String id;

  @Indexed
  @NonNull
  private String requesterId;

  @Indexed
  @NonNull
  private String driverId;

  @Indexed
  @NonNull
  private String type;
}
