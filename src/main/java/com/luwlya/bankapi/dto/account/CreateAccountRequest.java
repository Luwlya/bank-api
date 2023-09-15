package com.luwlya.bankapi.dto.account;

import com.luwlya.bankapi.model.Currency;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateAccountRequest(
        @NotNull
        String name,
        @NotNull
        Currency currency,
        @NotNull
        BigDecimal balance,
        @NotNull
        UUID customerId) {
}
