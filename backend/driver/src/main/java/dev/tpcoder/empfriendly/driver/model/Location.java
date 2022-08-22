package dev.tpcoder.empfriendly.driver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Location(
    String id,
    String referenceId,
    String driverId,
    long timestamp,
    @JsonProperty("geoPoint")
    Point point
) {

}
