package com.luwlya.bankapi.exception;

public class CustomerAlreadyExistsException extends RuntimeException {
    public CustomerAlreadyExistsException(String email) {
        super("Customer with " + email + " already exists");
    }
}
