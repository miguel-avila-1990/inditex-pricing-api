package com.inditex.pricing.domain.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidInputExceptionTest {

    @Test
    void shouldThrowInvalidInputException() {
        // Arrange
        String message = "Invalid input parameters";

        // Act
        InvalidInputException exception = new InvalidInputException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }
}
