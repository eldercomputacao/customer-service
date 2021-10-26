package com.elderpereira.customerservice.exceptions;

import java.time.LocalDateTime;
import java.util.List;

class ValidationExceptionDetails extends ExceptionDetails {

    private List<Error> errors;

    public ValidationExceptionDetails() {
    }

    public ValidationExceptionDetails(String title, int status, String details, String developerMessage, LocalDateTime dateTime, List<Error> errors) {
        super(title, status, details, developerMessage, dateTime);
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
