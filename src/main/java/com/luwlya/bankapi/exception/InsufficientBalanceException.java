package com.luwlya.bankapi.exception;

import java.util.UUID;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(UUID id) {
        super("Account " + id + " has insufficient balance.");
    }
}
