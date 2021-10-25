package com.elderpereira.customerservice.exceptions;

import java.time.LocalDateTime;

public class ValidationExceptionDetails extends ExceptionDetails {
    private String fields;
    private String fieldsMessage;

    public ValidationExceptionDetails() {

    }

    public ValidationExceptionDetails(String title, int status, String details, String developerMessage, LocalDateTime dateTime, String fields, String fieldsMessage) {
        super(title, status, details, developerMessage, dateTime);
        this.fields = fields;
        this.fieldsMessage = fieldsMessage;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getFieldsMessage() {
        return fieldsMessage;
    }

    public void setFieldsMessage(String fieldsMessage) {
        this.fieldsMessage = fieldsMessage;
    }
}
