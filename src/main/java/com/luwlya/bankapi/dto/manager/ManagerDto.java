package com.luwlya.bankapi.dto.manager;

import com.luwlya.bankapi.model.ManagerStatus;

import java.time.OffsetDateTime;
import java.util.UUID;

public record ManagerDto(
        UUID id,
        String firstName,
        String lastName,
        String email,
        ManagerStatus status,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
