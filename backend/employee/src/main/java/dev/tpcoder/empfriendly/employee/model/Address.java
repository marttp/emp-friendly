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
public class Address {

  @NonNull
  @Indexed
  private String houseNumber;

  @NonNull
  @Indexed
  private String city;

  @NonNull
  @Indexed
  private String state;

  @NonNull
  @Indexed
  private String postalCode;

  @NonNull
  @Indexed
  private String country;
}
