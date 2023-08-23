package com.luwlya.bankapi.service;

import com.luwlya.bankapi.dto.CreateCustomerRequest;
import com.luwlya.bankapi.dto.CustomerDto;
import com.luwlya.bankapi.dto.CustomersListDto;

import java.util.UUID;

public interface CustomerService {
    CustomerDto createCustomer(CreateCustomerRequest request);

    CustomerDto getCustomer(UUID id);

    CustomersListDto getAllCustomers();

    CustomerDto updateCustomer(String id, CreateCustomerRequest update);

    void deleteCustomer(String id);
}
