package com.luwlya.bankapi.repository;

import com.luwlya.bankapi.model.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface CustomerRepository {
    void insert(Customer customer) throws SQLException;

    Customer get(UUID id);

    List<Customer> getAll();

    void update(Customer customer);

    boolean delete(UUID id);
}
