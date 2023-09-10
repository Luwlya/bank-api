package com.luwlya.bankapi.service;

import com.luwlya.bankapi.dto.account.AccountDto;
import com.luwlya.bankapi.dto.account.AccountsListDto;
import com.luwlya.bankapi.dto.account.CreateAccountRequest;
import com.luwlya.bankapi.dto.account.UpdateAccountRequest;

import java.util.UUID;

public interface AccountService {
    AccountDto createAccount(CreateAccountRequest request);

    AccountDto getAccount(UUID id);

    AccountsListDto getAllAccounts();

    AccountDto updateAccount(UUID id, UpdateAccountRequest update);

    void deleteAccount(UUID id);
}
