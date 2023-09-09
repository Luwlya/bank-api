package com.luwlya.bankapi.dto.transaction;

import java.time.OffsetDateTime;

public record CreateTransactionRequest(
        String debitId,
        String creditId,
        String amount,
        String description,
        OffsetDateTime createdAt
) {
}