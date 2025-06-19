package com.inditex.pricing.infrastructure.in.rest.exceptions;

import static com.inditex.pricing.infrastructure.in.rest.exceptions.codes.ErrorCodes.BUSINESS_ERROR;
import static com.inditex.pricing.infrastructure.in.rest.exceptions.codes.ErrorCodes.INVALID_INPUT;
import static com.inditex.pricing.infrastructure.in.rest.exceptions.codes.ErrorCodes.MISSING_PARAMETER;
import static com.inditex.pricing.infrastructure.in.rest.exceptions.codes.ErrorCodes.PRICE_NOT_FOUND;
import static com.inditex.pricing.infrastructure.in.rest.exceptions.codes.ErrorCodes.TYPE_MISMATCH;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.inditex.pricing.domain.exceptions.DomainException;
import com.inditex.pricing.domain.exceptions.InvalidInputException;
import com.inditex.pricing.domain.exceptions.PriceNotFoundException;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePriceNotFoundException(PriceNotFoundException ex) {
        log.warn("Price not found: {}", ex.getMessage());
        return buildError(PRICE_NOT_FOUND, ex.getMessage(), HttpStatus.NOT_FOUND);
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
    
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleDomainException(DomainException ex) {
        log.warn("Domain error: {}", ex.getMessage());
        return buildError(BUSINESS_ERROR, ex.getMessage(), HttpStatus.BAD_REQUEST);
    }



    private ResponseEntity<ErrorResponse> buildError(String code, String message, HttpStatus status) {
        return new ResponseEntity<>(new ErrorResponse(code, message), status);
    }
}
