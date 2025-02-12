package br.com.devjmcn.api_temperature_control.infra.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private final int statusCode;
    private final String message;
    private final String timestamp = LocalDateTime.now().toString();
    ;

    public ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
