package com.luwlya.bankapi.repository;

import com.luwlya.bankapi.model.Customer;

import java.util.List;

public interface CustomerRepository {
    void insert(Customer customer);

    Customer get(String id);

    List<Customer> getAll();

    void update(Customer customer);

    void delete(String id);
}
