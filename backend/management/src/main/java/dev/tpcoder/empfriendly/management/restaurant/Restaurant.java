package dev.tpcoder.empfriendly.management.restaurant;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Restaurant(
    @JsonProperty("restaurant_id")
    String restaurantId,
    String name) {

}
