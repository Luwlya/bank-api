package com.luwlya.bankapi.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record TransactionDto(
        UUID id,
        String debitId,
        String creditId,
        String amount,
        String description,
        OffsetDateTime createdAt
) {
}
