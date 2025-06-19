package com.inditex.pricing.domain.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceNotFoundExceptionTest {

    @Test
    void shouldCreateExceptionWithMessage() {
        // Arrange
        String message = "No applicable price found";

        // Act
        PriceNotFoundException exception = new PriceNotFoundException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }

    @Test
    void shouldCreateExceptionWithMessageAndCause() {
        // Arrange
        String message = "No applicable price found";
        Throwable cause = new RuntimeException("Database error");

        // Act
        PriceNotFoundException exception = new PriceNotFoundException(message, cause);

        // Assert
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
