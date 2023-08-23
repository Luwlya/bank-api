package com.luwlya.bankapi.dto;

import java.time.OffsetDateTime;

public record TransactionDto(
        String id,
        String debitId,
        String creditId,
        String amount,
        String description,
        OffsetDateTime createdAt
) {
}
