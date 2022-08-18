package dev.tpcoder.empfriendly.point.model.dto;

import dev.tpcoder.empfriendly.point.enumeration.PointProcessingType;
import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record DeductRequest(
    @NotNull
    PointProcessingType type,

    @NotBlank
    String referenceId,

    @NotNull
    BigDecimal point
) {

}
