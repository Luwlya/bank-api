package com.luwlya.bankapi.service;

import com.luwlya.bankapi.dto.CreateCustomerRequest;
import com.luwlya.bankapi.dto.CustomerDto;
import com.luwlya.bankapi.dto.CustomersListDto;
import com.luwlya.bankapi.dto.UpdateCustomerRequest;

import java.util.UUID;

public interface CustomerService {
    CustomerDto createCustomer(CreateCustomerRequest request);

    CustomerDto getCustomer(UUID id);

    CustomersListDto getAllCustomers();

    CustomerDto updateCustomer(UUID id, UpdateCustomerRequest update);

    void deleteCustomer(UUID id);
}
