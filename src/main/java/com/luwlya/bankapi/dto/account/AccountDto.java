package com.luwlya.bankapi.dto.account;

import com.luwlya.bankapi.model.AccountStatus;
import com.luwlya.bankapi.model.Currency;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record AccountDto(
        UUID id,
        UUID customerId,
        String name,
        BigDecimal balance,
        Currency currency,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        AccountStatus status
) {
}
