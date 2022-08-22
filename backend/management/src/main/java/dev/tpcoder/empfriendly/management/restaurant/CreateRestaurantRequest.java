package dev.tpcoder.empfriendly.management.restaurant;

import javax.validation.constraints.NotNull;

public record CreateRestaurantRequest(
    @NotNull
    String id,
    @NotNull
    String name) {
}
