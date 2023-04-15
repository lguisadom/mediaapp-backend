package com.lagm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ResponseExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomerErrorResponse> handleAllException(ModelNotFoundException ex, WebRequest request) {
        CustomerErrorResponse err = new CustomerErrorResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<CustomerErrorResponse> handleModelNotFoundException(ModelNotFoundException ex, WebRequest request) {
        CustomerErrorResponse err = new CustomerErrorResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    // Since Spring Boot 3
    /*@ExceptionHandler(ModelNotFoundException.class)
    public ProblemDetail handleModelNotFoundException(ModelNotFoundException ex, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Model not found");
        problemDetail.setType(URI.create("/not-found"));
        problemDetail.setProperty("extra", "extra-value");
        problemDetail.setProperty("extra-number", 32);

        return problemDetail;
    }*/

    // Since Spring Boot 3
    /*@ExceptionHandler(ModelNotFoundException.class)
    public ErrorResponse handleModelNotFoundException(ModelNotFoundException ex, WebRequest request) {
        return ErrorResponse.builder(ex, HttpStatus.NOT_FOUND, ex.getMessage())
                .title("Model not found")
                .type(URI.create(request.getContextPath()))
                .property("extra", "extra-value")
                .property("extra-number", 32)
                .build();
    }*/
}
