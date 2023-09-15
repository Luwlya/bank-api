package com.luwlya.bankapi.dto.transaction;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateTransactionRequest(
        @NotNull
        UUID debitAccountId,
        @NotNull
        UUID creditAccountId,
        @NotNull
        BigDecimal amount,
        @NotBlank
        String description
) {
}
