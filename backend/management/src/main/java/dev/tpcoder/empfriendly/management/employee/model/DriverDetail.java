package dev.tpcoder.empfriendly.management.employee.model;

import lombok.NonNull;

public record DriverDetail(
    @NonNull
    String driverLicense
) {

}
