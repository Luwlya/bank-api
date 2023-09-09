package com.luwlya.bankapi.dto.account;

import java.time.OffsetDateTime;

public record CustomerAccountDto(
        String id,
        String customerId,
        String name,
        String balance,
        String currency,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
