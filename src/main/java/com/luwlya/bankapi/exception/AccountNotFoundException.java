package com.luwlya.bankapi.exception;

import java.util.UUID;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(UUID id) {
        super("Account " + id + " is not found.");
    }
}
