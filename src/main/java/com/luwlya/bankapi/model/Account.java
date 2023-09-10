package com.luwlya.bankapi.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record Account(
        UUID id,
        UUID customerId,
        String name,
        BigDecimal balance,
        Currency currency,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
