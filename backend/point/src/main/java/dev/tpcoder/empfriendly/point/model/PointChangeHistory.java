package dev.tpcoder.empfriendly.point.model;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
@Data
@Document
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PointChangeHistory {

  @Id
  private String id;

  @Indexed
  @NotNull
  private String referenceId;

  @NotNull
  private BigDecimal point;

  @NotNull
  private BigDecimal balancePoint;

  @CreatedDate
  private LocalDateTime createdDate;
}
