package com.elderpereira.customerservice.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleBadRequestException(CustomerNotFoundException exception) {

        ExceptionDetails details = new ExceptionDetails();
        details.setTitle("Not Found");
        details.setStatus(HttpStatus.NOT_FOUND.value());
        details.setDetails(exception.getMessage());
        details.setDeveloperMessage(exception.getClass().getName());
        details.setDateTime(LocalDateTime.now());

        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FieldInvalidException.class)
    public ResponseEntity<ExceptionDetails> handleBadRequestException(FieldInvalidException exception) {

        ExceptionDetails details = new ExceptionDetails();
        details.setTitle("Field Invalid");
        details.setStatus(HttpStatus.BAD_REQUEST.value());
        details.setDetails(exception.getMessage());
        details.setDeveloperMessage(exception.getClass().getName());
        details.setDateTime(LocalDateTime.now());

        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDetails> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {

        ExceptionDetails details = new ExceptionDetails();
        details.setTitle("Bad Request");
        details.setStatus(HttpStatus.BAD_REQUEST.value());
        details.setDetails(exception.getRootCause().getMessage());
        details.setDeveloperMessage(exception.getClass().getName());
        details.setDateTime(LocalDateTime.now());

        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<Error> errors = new ArrayList<>();
        exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .forEach((fieldError) -> {
                    errors.add(new Error(fieldError.getField(), fieldError.getDefaultMessage()));
                });

        ValidationExceptionDetails validationDetails = new ValidationExceptionDetails();
        validationDetails.setTitle("Bad Request, Invalid Fields");
        validationDetails.setDetails("Check the field(s) error");
        validationDetails.setErrors(errors);
        validationDetails.setDeveloperMessage(exception.getClass().getName());
        validationDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        validationDetails.setDateTime(LocalDateTime.now());

        return new ResponseEntity<>(validationDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            @Nullable Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        ExceptionDetails details = new ExceptionDetails();
        details.setTitle("Bad Request");
        details.setStatus(status.value());
        details.setDetails(ex.getCause().getMessage());
        details.setDeveloperMessage(ex.getClass().getName());
        details.setDateTime(LocalDateTime.now());

        return new ResponseEntity<>(details, headers, status);
    }


}
