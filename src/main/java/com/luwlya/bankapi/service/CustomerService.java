package com.luwlya.bankapi.service;

import com.luwlya.bankapi.dto.customer.CreateCustomerRequest;
import com.luwlya.bankapi.dto.customer.CustomerDto;
import com.luwlya.bankapi.dto.customer.CustomersListDto;
import com.luwlya.bankapi.dto.customer.UpdateCustomerRequest;

import java.util.UUID;

public interface CustomerService {
    CustomerDto createCustomer(CreateCustomerRequest request);

    CustomerDto getCustomer(UUID id);

    CustomersListDto getAllCustomers();

    CustomerDto updateCustomer(UUID id, UpdateCustomerRequest update);

    void deleteCustomer(UUID id);
}
