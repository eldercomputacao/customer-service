package com.elderpereira.customerservice.exceptions;

public class FieldInvalidException extends RuntimeException{
    public FieldInvalidException(String message) {
        super(message);
    }
}
