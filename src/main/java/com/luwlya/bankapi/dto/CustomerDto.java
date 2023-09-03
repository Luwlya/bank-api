package com.luwlya.bankapi.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record CustomerDto(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String address,
        String phone,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
    
}
