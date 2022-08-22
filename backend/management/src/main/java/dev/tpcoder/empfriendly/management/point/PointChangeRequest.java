package dev.tpcoder.empfriendly.management.point;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record PointChangeRequest(
    @NotNull
    PointProcessingType type,

    @NotBlank
    String referenceId,

    @NotNull
    BigDecimal point
) {

}
