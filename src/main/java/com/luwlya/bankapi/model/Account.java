package com.luwlya.bankapi.model;

import io.soabase.recordbuilder.core.RecordBuilder;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Class models account entity
 * @param id
 * @param customerId
 * @param name
 * @param balance
 * @param currency
 * @param createdAt
 * @param updatedAt
 * @param status
 */
@RecordBuilder
public record Account(
        UUID id,
        UUID customerId,
        String name,
        BigDecimal balance,
        Currency currency,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        AccountStatus status
) implements AccountBuilder.With {
}
