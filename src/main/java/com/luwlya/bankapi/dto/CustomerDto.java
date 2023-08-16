package com.luwlya.bankapi.dto;

import java.time.OffsetDateTime;

public record CustomerDto(
        String id,
        String firstName,
        String lastName,
        String email,
        String address,
        String phone,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
    
}
