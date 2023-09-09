package com.luwlya.bankapi.exception;

import java.util.UUID;

public class ManagerNotFoundException extends RuntimeException {
    public ManagerNotFoundException(UUID id) {
        super("Manager " + id + " is not found.");
    }
}
