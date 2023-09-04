package com.luwlya.bankapi.model;

import java.time.OffsetDateTime;
import java.util.UUID;

public record Customer(
        UUID id,
        CustomerStatus status,
        String firstName,
        String lastName,
        String email,
        String address,
        String phone,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
