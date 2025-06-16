package com.inditex.pricing.infrastructure.in.rest.exceptions;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class ErrorResponse {
    private final String error;
    private final String message;
    private final LocalDateTime timestamp;

    public ErrorResponse(String error, String message) {
        this.error = error;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}

