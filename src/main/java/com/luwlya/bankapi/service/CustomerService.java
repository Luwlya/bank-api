package com.luwlya.bankapi.service;

import com.luwlya.bankapi.dto.CreateCustomerRequest;
import com.luwlya.bankapi.dto.CustomerDto;
import com.luwlya.bankapi.dto.CustomersListDto;

public interface CustomerService {
    CustomerDto createCustomer(CreateCustomerRequest request);

    CustomerDto getCustomer(String id);

    CustomersListDto getAllCustomers();

    CustomerDto updateCustomer(String id, CreateCustomerRequest update);

    void deleteCustomer(String id);
}
