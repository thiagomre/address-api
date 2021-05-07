package com.stoom.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
public class DefaultErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<ErrorDetails> handleCobPayloadNotFoundException(ConstraintViolationException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));
        var response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
        return response;
    }

    @ExceptionHandler({ AddressNotFoundException.class })
    public ResponseEntity<ErrorDetails> handleCobPayloadNotFoundException(AddressNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));
        var response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
        return response;
    }
}
