package com.luwlya.bankapi.repository;

import com.luwlya.bankapi.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(Customer customer) {
        jdbcTemplate.update("insert into customers(id,first_name,last_name,email,address,phone,created_at,updated_at) " +
                        "values (?,?,?,?,?,?,?,?)",
                customer.id(),
                customer.firstName(),
                customer.lastName(),
                customer.email(),
                customer.address(),
                customer.phone(),
                customer.createdAt(),
                customer.updatedAt());
    }

    @Override
    public Customer get(String id) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void delete(String id) {

    }
}
