package com.luwlya.bankapi.dto.transaction;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record CreateTransactionRequest(
        UUID debitAccountId,
        UUID creditAccountId,
        BigDecimal amount,
        String description,
        OffsetDateTime createdAt
) {
}
