package dev.tpcoder.empfriendly.driver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Point(
    @JsonProperty("y")
    String latitude,
    @JsonProperty("x")
    String longitude
) {

}
