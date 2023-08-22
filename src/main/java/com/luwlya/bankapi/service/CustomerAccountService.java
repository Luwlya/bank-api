package com.luwlya.bankapi.service;

import com.luwlya.bankapi.dto.CreateCustomerAccountRequest;
import com.luwlya.bankapi.dto.CustomerAccountDto;
import com.luwlya.bankapi.dto.CustomerAccountsListDto;
import com.luwlya.bankapi.dto.UpdateCustomerAccountRequest;

public interface CustomerAccountService {
    CustomerAccountDto createCustomerAccount(CreateCustomerAccountRequest request);

    CustomerAccountDto getCustomerAccount(String id);

    CustomerAccountsListDto getAllCustomerAccounts();

    CustomerAccountDto updateCustomerAccount(String id, UpdateCustomerAccountRequest update);

    void deleteCustomerAccount(String id);
}
