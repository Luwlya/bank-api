package com.luwlya.bankapi.dto.account;

import com.luwlya.bankapi.model.AccountStatus;
import com.luwlya.bankapi.model.Currency;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * POJO (plain old java object) for account
 *
 * @param id         identifier of account
 * @param customerId identifier of customer to whom this account belongs to
 * @param name       name of the account
 * @param balance    current balance of the account
 * @param currency   currency of the account
 * @param createdAt  time of the account's creation
 * @param updatedAt  last time the account was updated
 * @param status     current status of the account
 */
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
