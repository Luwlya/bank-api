package com.luwlya.bankapi.service.impl;

import com.luwlya.bankapi.dto.customer.CreateCustomerRequest;
import com.luwlya.bankapi.dto.customer.CustomerDto;
import com.luwlya.bankapi.dto.customer.CustomersListDto;
import com.luwlya.bankapi.dto.customer.UpdateCustomerRequest;
import com.luwlya.bankapi.exception.CustomerNotFoundException;
import com.luwlya.bankapi.model.Customer;
import com.luwlya.bankapi.model.CustomerStatus;
import com.luwlya.bankapi.repository.CustomerRepository;
import com.luwlya.bankapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Implements main operations on customers
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Creates customer and persists it in db
     *
     * @param request parameters for customer creation
     * @return dto of created customer
     */
    @Override
    public CustomerDto createCustomer(CreateCustomerRequest request) {
        Customer customer = new Customer(UUID.randomUUID(),
                CustomerStatus.ACTIVE,
                request.firstName(),
                request.lastName(),
                request.email(),
                request.address(),
                request.phone(),
                OffsetDateTime.now(),
                OffsetDateTime.now());
        customerRepository.insert(customer);
        return dto(customer);
    }

    private CustomerDto dto(Customer customer) {
        return new CustomerDto(customer.id(),
                customer.status(),
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
    public CustomerDto updateCustomer(UUID id, UpdateCustomerRequest update) {
        Customer customer = customerRepository.get(id);
        Customer updatedCustomer = new Customer(id,
                customer.status(),
                update.firstName() != null ? update.firstName() : customer.firstName(),
                update.lastName() != null ? update.lastName() : customer.lastName(),
                update.email() != null ? update.email() : customer.email(),
                update.address() != null ? update.address() : customer.address(),
                update.phone() != null ? update.phone() : customer.phone(),
                customer.createdAt(),
                OffsetDateTime.now());
        customerRepository.update(updatedCustomer);
        return dto(updatedCustomer);
    }

    @Override
    public void deleteCustomer(UUID id) {
        boolean deleted = customerRepository.delete(id);
        if (deleted) {
            System.out.println("Customer " + id + " has been deleted");
        } else {
            throw new CustomerNotFoundException(id);
        }
    }
}
