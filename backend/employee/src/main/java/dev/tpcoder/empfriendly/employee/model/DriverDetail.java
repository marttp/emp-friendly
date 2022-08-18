package dev.tpcoder.empfriendly.employee.model;

import com.redis.om.spring.annotations.Indexed;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class DriverDetail {

  @NonNull
  @Indexed
  private String driverLicense;
}
