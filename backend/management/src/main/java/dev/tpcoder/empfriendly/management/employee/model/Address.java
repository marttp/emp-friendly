package dev.tpcoder.empfriendly.management.employee.model;

import lombok.NonNull;

public record Address(

    @NonNull
    String houseNumber,

    @NonNull
    String city,

    @NonNull
    String state,

    @NonNull
    String postalCode,

    @NonNull
    String country
) {

}
