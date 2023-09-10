package com.luwlya.bankapi.dto.account;

import com.luwlya.bankapi.model.Currency;

import java.math.BigDecimal;

public record CreateAccountRequest(Currency currency, BigDecimal balance) {
}
