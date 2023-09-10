package com.luwlya.bankapi.service.impl;

import com.luwlya.bankapi.dto.account.AccountDto;
import com.luwlya.bankapi.dto.account.AccountsListDto;
import com.luwlya.bankapi.dto.account.CreateAccountRequest;
import com.luwlya.bankapi.dto.account.UpdateAccountRequest;
import com.luwlya.bankapi.model.Currency;
import com.luwlya.bankapi.service.AccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public AccountDto createAccount(CreateAccountRequest request) {
        return new AccountDto(UUID.randomUUID(),
                UUID.randomUUID(),
                "name",
                request.balance(),
                request.currency(),
                OffsetDateTime.now(),
                OffsetDateTime.now());
    }

    @Override
    public AccountDto getAccount(UUID id) {
        return new AccountDto(id,
                UUID.randomUUID(),
                "Stepan Bandera",
                new BigDecimal(1000),
                Currency.EUR,
                OffsetDateTime.now(),
                OffsetDateTime.now());
    }

    @Override
    public AccountsListDto getAllAccounts() {
        return new AccountsListDto(List.of());
    }

    @Override
    public AccountDto updateAccount(UUID id, UpdateAccountRequest update) {
        return new AccountDto(id,
                UUID.randomUUID(),
                "Zalujnii",
                update.balance(),
                Currency.EUR,
                OffsetDateTime.now(),
                OffsetDateTime.now());
    }

    @Override
    public void deleteAccount(UUID id) {
        System.out.println("Account " + id + " has been deleted");
    }
}
