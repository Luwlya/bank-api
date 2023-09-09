package com.luwlya.bankapi.dto.customer;

import com.luwlya.bankapi.model.CustomerStatus;

import java.time.OffsetDateTime;
import java.util.UUID;

public record CustomerDto(
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
