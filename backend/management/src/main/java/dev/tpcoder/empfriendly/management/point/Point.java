package dev.tpcoder.empfriendly.management.point;


import java.math.BigDecimal;

public record Point (
    String referenceId,
    BigDecimal current,
    String type
) {
}
