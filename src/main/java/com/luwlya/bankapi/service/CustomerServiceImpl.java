package com.luwlya.bankapi.service;

import com.luwlya.bankapi.dto.CreateCustomerRequest;
import com.luwlya.bankapi.dto.CustomerDto;
import com.luwlya.bankapi.dto.CustomersListDto;
import com.luwlya.bankapi.model.Customer;
import com.luwlya.bankapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDto createCustomer(CreateCustomerRequest request) {
        Customer customer = new Customer(UUID.randomUUID(),
                request.firstName(),
                request.lastName(),
                request.email(),
                request.address(),
                request.phone(),
                OffsetDateTime.now(),
                OffsetDateTime.now());
        System.out.println(request);
        customerRepository.insert(customer);
        return dto(customer);
    }

    private CustomerDto dto(Customer customer) {
        return new CustomerDto(customer.id(),
                customer.firstName(),
                customer.lastName(),
                customer.email(),
                customer.address(),
                customer.phone(),
                customer.createdAt(),
                customer.updatedAt());
    }

    @Override
    public CustomerDto getCustomer(UUID id) {
        Customer customer = customerRepository.get(id);
        return dto(customer);
    }

    @Override
    public CustomersListDto getAllCustomers() {
        List<Customer> customers = customerRepository.getAll();
        List<CustomerDto> result = customers.stream().map(this::dto).toList();
        return new CustomersListDto(result);
    }

    @Override
    public CustomerDto updateCustomer(UUID id, CreateCustomerRequest update) {
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
    public boolean deleteCustomer(UUID id) {
        boolean deleted = customerRepository.delete(id);
        if (deleted) {
            System.out.println("Customer " + id + " has been deleted");
        }
        return deleted;
    }
}
