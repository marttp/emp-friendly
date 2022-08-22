package dev.tpcoder.empfriendly.general.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

public record ErrorResponse(
    @JsonProperty("error")
    String error,

    @JsonProperty("error_description")
    String errorDescription,

    @JsonProperty("error_status")
    int errorStatus,

    @JsonProperty("error_at")
    LocalDateTime errorAt
) {
    public static ErrorResponse serverError(String message) {
        return new ErrorResponse(
            "server_error",
            message,
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            LocalDateTime.now()
        );
    }
}
