package com.inditex.pricing.infrastructure.in.rest.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.inditex.pricing.domain.exceptions.InvalidInputException;
import com.inditex.pricing.domain.exceptions.PriceNotFoundException;

import static com.inditex.pricing.infrastructure.in.rest.exceptions.codes.ErrorCodes.*;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePriceNotFoundException(PriceNotFoundException ex) {
        log.warn("Price not found: {}", ex.getMessage());
        return buildError(PRICE_NOT_FOUND, ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidParametersException.class)
    public ResponseEntity<ErrorResponse> handleInvalidParametersException(InvalidParametersException ex) {
        log.warn("Invalid parameter: {}", ex.getMessage());
        return buildError(INVALID_PARAMETER, ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.warn("Missing request parameter: {}", ex.getMessage());
        return buildError(MISSING_PARAMETER, ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.warn("Parameter type mismatch: {}", ex.getMessage());
        return buildError(TYPE_MISMATCH, ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInputException(InvalidInputException ex) {
        log.warn("Invalid input: {}", ex.getMessage());
        return buildError(INVALID_INPUT, ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> buildError(String code, String message, HttpStatus status) {
        return new ResponseEntity<>(new ErrorResponse(code, message), status);
    }
}
