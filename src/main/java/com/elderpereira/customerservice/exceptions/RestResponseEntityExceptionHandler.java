package com.elderpereira.customerservice.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        ValidationExceptionDetails validationDetails = new ValidationExceptionDetails();
        validationDetails.setTitle("Bad Request, Invalid Fields");
        validationDetails.setDetails("Check the field(s) error");
        validationDetails.setFields(fields);
        validationDetails.setFieldsMessage(fieldsMessage);
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
