package com.luwlya.bankapi.service;

import com.luwlya.bankapi.dto.CreateCustomerRequest;
import com.luwlya.bankapi.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerDto createCustomer(CreateCustomerRequest request) {
        CustomerDto dto = new CustomerDto("id1",
                request.firstName(),
                request.lastName(),
                request.email(),
                request.address(),
                request.phone(),
                OffsetDateTime.now(),
                OffsetDateTime.now());
        System.out.println(request);
        return dto;
    }
}
