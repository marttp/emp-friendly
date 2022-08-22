package dev.tpcoder.empfriendly.driver.model;

import dev.tpcoder.empfriendly.driver.enumeration.DriveType;
import java.time.LocalDateTime;

public record OrderResponse (
    String id,
    DriveType type,
    LocalDateTime createdDate

) {

}