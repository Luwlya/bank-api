package com.luwlya.bankapi.exception;

import java.util.UUID;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(UUID id) {
        super("Transaction " + id + " is not found.");
    }
}
