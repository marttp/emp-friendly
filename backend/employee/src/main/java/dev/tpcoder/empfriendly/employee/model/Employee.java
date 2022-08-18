package dev.tpcoder.empfriendly.employee.model;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.geo.Point;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Employee {

  @Id
  private String id;

  @Searchable
  @NonNull
  private String firstName;

  @Searchable
  @NonNull
  private String lastName;

  @Indexed
  @NonNull
  private Integer age;

  @Searchable
  @NonNull
  private String email;

  @Indexed
  @NonNull
  private Point addressLoc;

  @Indexed
  @NonNull
  private Address address;

  @Indexed
  @NonNull
  private Set<String> tags;

  @Indexed
  @NonNull
  private String type;

  @Indexed
  private DriverDetail driverDetail;

  @CreatedDate
  private LocalDateTime createdDate;

  @LastModifiedDate
  private LocalDateTime lastModifiedDate;
}
