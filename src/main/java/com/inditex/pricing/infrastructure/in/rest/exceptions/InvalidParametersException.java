package com.inditex.pricing.infrastructure.in.rest.exceptions;

/**
 * Excepción lanzada cuando los parámetros de entrada en una petición REST no son válidos.
 */
public class InvalidParametersException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidParametersException(String message) {
        super(message);
    }

    public InvalidParametersException(String message, Throwable cause) {
        super(message, cause);
    }
}
