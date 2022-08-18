package dev.tpcoder.empfriendly.shipment.model;

public record OrderShipmentEvent(
    String employeeId,
    String orderId,
    Address address
) implements ShipmentEvent {

}
