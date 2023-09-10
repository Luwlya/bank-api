package com.luwlya.bankapi.dto.account;

import java.math.BigDecimal;

public record UpdateAccountRequest(BigDecimal balance) {
}
