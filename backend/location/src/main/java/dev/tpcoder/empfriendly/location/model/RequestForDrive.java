package dev.tpcoder.empfriendly.location.model;


public record RequestForDrive(
    String referenceId,
    String requesterId,
    String driverId,
    DriveType type
) {

}
