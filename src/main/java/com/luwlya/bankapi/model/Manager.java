package com.luwlya.bankapi.model;

import java.time.OffsetDateTime;
import java.util.UUID;

public record Manager(
        UUID id,
        ManagerStatus status,
        String firstName,
        String lastName,
        String email,
        String passwordHash,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
