package com.luwlya.bankapi.service;

import com.luwlya.bankapi.dto.account.CreateCustomerAccountRequest;
import com.luwlya.bankapi.dto.account.CustomerAccountDto;
import com.luwlya.bankapi.dto.account.CustomerAccountsListDto;
import com.luwlya.bankapi.dto.account.UpdateCustomerAccountRequest;

public interface CustomerAccountService {
    CustomerAccountDto createCustomerAccount(CreateCustomerAccountRequest request);

    CustomerAccountDto getCustomerAccount(String id);

    CustomerAccountsListDto getAllCustomerAccounts();

    CustomerAccountDto updateCustomerAccount(String id, UpdateCustomerAccountRequest update);

    void deleteCustomerAccount(String id);
}
