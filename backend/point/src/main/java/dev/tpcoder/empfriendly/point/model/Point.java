package dev.tpcoder.empfriendly.point.model;


import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Point {

  @Id
  @Indexed
  private String referenceId;

  @Indexed
  @NotNull
  private BigDecimal current;

  @Indexed
  @NotNull
  private String type;
}
