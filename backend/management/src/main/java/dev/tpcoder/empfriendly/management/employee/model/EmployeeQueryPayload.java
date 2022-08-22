package dev.tpcoder.empfriendly.management.employee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class EmployeeQueryPayload {

  private String id;

  private String firstName;

  private String lastName;

  private Integer age;

  private String email;

  private Point addressLoc;

  private Double searchRadius;

  private String houseNumber;

  private String city;

  private String state;

  private String postalCode;

  private String country;

  private Set<String> tags;

  private String type;

  private String driverLicense;

  private LocalDateTime createdDate;

  private LocalDateTime lastModifiedDate;
}
