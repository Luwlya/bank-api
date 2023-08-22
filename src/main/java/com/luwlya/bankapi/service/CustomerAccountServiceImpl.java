package com.luwlya.bankapi.service;

import com.luwlya.bankapi.dto.CreateCustomerAccountRequest;
import com.luwlya.bankapi.dto.CustomerAccountDto;
import com.luwlya.bankapi.dto.CustomerAccountsListDto;
import com.luwlya.bankapi.dto.UpdateCustomerAccountRequest;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {
    @Override
    public CustomerAccountDto createCustomerAccount(CreateCustomerAccountRequest request) {
        return new CustomerAccountDto("id1",
                "customerId12",
                "name",
                request.balance(),
                request.currency(),
                OffsetDateTime.now(),
                OffsetDateTime.now());
    }

    @Override
    public CustomerAccountDto getCustomerAccount(String id) {
        return new CustomerAccountDto(id,
                "001",
                "Stepan Bandera",
                "1000",
                "EUR",
                OffsetDateTime.now(),
                OffsetDateTime.now());
    }

    @Override
    public CustomerAccountsListDto getAllCustomerAccounts() {
        return new CustomerAccountsListDto(List.of());
    }

    @Override
    public CustomerAccountDto updateCustomerAccount(String id, UpdateCustomerAccountRequest update) {
        return new CustomerAccountDto(id,
                "007",
                "Zalujnii",
                update.balance(),
                update.currency(),
                OffsetDateTime.now(),
                OffsetDateTime.now());
    }

    @Override
    public void deleteCustomerAccount(String id) {
        System.out.println("Customer account " + id + " has been deleted");
    }
}
