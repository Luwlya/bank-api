package com.luwlya.bankapi.exception;

import java.util.UUID;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(UUID id) {
        super("Customer " + id + " is not found.");
    }
}
