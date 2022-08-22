package dev.tpcoder.empfriendly.management.employee.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Employee {

  private String id;

  @NonNull
  private String firstName;

  @NonNull
  private String lastName;

  @NonNull
  private Integer age;

  @NonNull
  private String email;

  @NonNull
  private Point addressLoc;

  @NonNull
  private Address address;

  @NonNull
  private Set<String> tags;

  @NonNull
  private String type;

  private DriverDetail driverDetail;

  private LocalDateTime createdDate;

  private LocalDateTime lastModifiedDate;
}
