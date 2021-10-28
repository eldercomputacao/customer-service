package com.elderpereira.customerservice.util;

import com.elderpereira.customerservice.exceptions.FieldInvalidException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldValidator {
    public static final Pattern VALID_EMAIL_REGEX =
            Pattern.compile(
                    "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                    Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_PHONE_REGEX =
            Pattern.compile(
                    "\\(\\d\\d\\)\\s\\d\\d\\d\\d\\d-\\d\\d\\d\\d",
                    Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_CPF_REGEX =
            Pattern.compile(
                    "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}",
                    Pattern.CASE_INSENSITIVE);

    public static void validateEmail(String email) {
        Matcher matcher = VALID_EMAIL_REGEX.matcher(email);
        if(!matcher.find()){
            throw new FieldInvalidException("Email Invalid");
        }
    }

    public static void validatePhone(String phone) {
        Matcher matcher = VALID_PHONE_REGEX.matcher(phone);
        if(!matcher.find()){
            throw new FieldInvalidException("Phone Invalid, Format (##) #####-####");
        }
    }

    public static void validateCpf(String cpf) {
        Matcher matcher = VALID_CPF_REGEX.matcher(cpf);
        if(!matcher.find() || cpf.length() != 14){
            throw new FieldInvalidException("Cpf Invalid, Format ###.###.###-##");
        }
    }

}
