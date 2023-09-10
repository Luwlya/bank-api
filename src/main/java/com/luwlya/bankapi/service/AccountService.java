package com.luwlya.bankapi.service;

import com.luwlya.bankapi.dto.account.AccountDto;
import com.luwlya.bankapi.dto.account.AccountsListDto;
import com.luwlya.bankapi.dto.account.CreateAccountRequest;
import com.luwlya.bankapi.dto.account.UpdateAccountRequest;

public interface AccountService {
    AccountDto createAccount(CreateAccountRequest request);

    AccountDto getAccount(String id);

    AccountsListDto getAllAccounts();

    AccountDto updateAccount(String id, UpdateAccountRequest update);

    void deleteAccount(String id);
}
