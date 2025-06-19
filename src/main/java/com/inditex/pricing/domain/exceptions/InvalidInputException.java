package com.inditex.pricing.domain.exceptions;

public class InvalidInputException extends DomainException {
    public InvalidInputException(String message) {
        super(message);
    }
}
