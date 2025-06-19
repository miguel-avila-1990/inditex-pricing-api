package com.inditex.pricing.domain.exceptions;

public class PriceNotFoundException extends DomainException {
    private static final long serialVersionUID = 1L;

    public PriceNotFoundException(String message) {
        super(message);
    }

    public PriceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
