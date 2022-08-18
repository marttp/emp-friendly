package dev.tpcoder.empfriendly.shipment.model;

public record Address(
    String houseNumber,
    String city,
    String state,
    String postalCode,
    String country
) {

}
