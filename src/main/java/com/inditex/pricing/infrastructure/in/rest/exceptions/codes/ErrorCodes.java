package com.inditex.pricing.infrastructure.in.rest.exceptions.codes;

/**
 * Códigos de error estándar utilizados en las respuestas de la API REST.
 * Cada código representa un tipo de error reconocido por el sistema.
 */
public final class ErrorCodes {

    private ErrorCodes() {}

    public static final String PRICE_NOT_FOUND = "PRICE_NOT_FOUND";
    public static final String MISSING_PARAMETER = "MISSING_PARAMETER";
    public static final String TYPE_MISMATCH = "TYPE_MISMATCH";
    public static final String INVALID_INPUT = "INVALID_INPUT";
    public static final String BUSINESS_ERROR = "BUSINESS_ERROR";
}
