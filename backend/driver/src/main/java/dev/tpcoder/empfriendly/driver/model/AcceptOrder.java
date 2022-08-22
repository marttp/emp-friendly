package dev.tpcoder.empfriendly.driver.model;

import dev.tpcoder.empfriendly.driver.enumeration.DriveType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record AcceptOrder(
    @NotBlank
    String id,

    @NotNull
    DriveType type
) {

}
