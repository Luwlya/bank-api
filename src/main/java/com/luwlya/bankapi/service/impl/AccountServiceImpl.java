package com.luwlya.bankapi.service.impl;

import com.luwlya.bankapi.dto.account.AccountDto;
import com.luwlya.bankapi.dto.account.AccountsListDto;
import com.luwlya.bankapi.dto.account.CreateAccountRequest;
import com.luwlya.bankapi.dto.account.UpdateAccountRequest;
import com.luwlya.bankapi.service.AccountService;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public AccountDto createAccount(CreateAccountRequest request) {
        return new AccountDto("id1",
                "customerId12",
                "name",
                request.balance(),
                request.currency(),
                OffsetDateTime.now(),
                OffsetDateTime.now());
    }

    @Override
    public AccountDto getAccount(String id) {
        return new AccountDto(id,
                "001",
                "Stepan Bandera",
                "1000",
                "EUR",
                OffsetDateTime.now(),
                OffsetDateTime.now());
    }

    @Override
    public AccountsListDto getAllAccounts() {
        return new AccountsListDto(List.of());
    }

    @Override
    public AccountDto updateAccount(String id, UpdateAccountRequest update) {
        return new AccountDto(id,
                "007",
                "Zalujnii",
                update.balance(),
                update.currency(),
                OffsetDateTime.now(),
                OffsetDateTime.now());
    }

    @Override
    public void deleteAccount(String id) {
        System.out.println("Customer account " + id + " has been deleted");
    }
}
