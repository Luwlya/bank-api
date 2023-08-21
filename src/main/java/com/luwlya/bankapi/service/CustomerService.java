package com.luwlya.bankapi.service;

import com.luwlya.bankapi.dto.CreateCustomerRequest;
import com.luwlya.bankapi.dto.CustomerDto;

public interface CustomerService {
    CustomerDto createCustomer(CreateCustomerRequest request);

}
