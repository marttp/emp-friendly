package dev.tpcoder.empfriendly.employee.model.message;

public record NewEmployeeComingEvent(
    String employeeId,
    String notificationType,
    String employeeEmail,
    String message
) implements NotificationEvent {

}
