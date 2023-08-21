package com.luwlya.bankapi.service;

import com.luwlya.bankapi.dto.CreateCustomerRequest;
import com.luwlya.bankapi.dto.CustomerDto;
import com.luwlya.bankapi.dto.CustomersListDto;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

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

    @Override
    public CustomerDto getCustomer(String id) {
        return new CustomerDto(id,
                "Ostap",
                "Vyshnya",
                "ovyshnya@ukr.com",
                "Kyiv",
                "+380670000001",
                OffsetDateTime.now(),
                OffsetDateTime.now());
    }

    @Override
    public CustomersListDto getAllCustomers() {
        return new CustomersListDto(List.of());
    }

    @Override
    public CustomerDto updateCustomer(String id, CreateCustomerRequest update) {
        return new CustomerDto(id,
                update.firstName(),
                update.lastName(),
                update.email(),
                update.address(),
                update.phone(),
                OffsetDateTime.now(),
                OffsetDateTime.now());
    }

    @Override
    public void deleteCustomer(String id) {
        System.out.println("Customer " + id + " has been deleted");
    }
}
