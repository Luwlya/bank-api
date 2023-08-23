package com.luwlya.bankapi.repository;

import com.luwlya.bankapi.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static Customer extractCustomer(ResultSet rs) throws SQLException {
        rs.next();
        return new Customer(rs.getObject("id", UUID.class),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("address"),
                rs.getString("phone"),
                rs.getObject("created_at", OffsetDateTime.class),
                rs.getObject("updated_at", OffsetDateTime.class));
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
    public Customer get(UUID id) {
        return jdbcTemplate.query("SELECT * FROM customers WHERE id = ?",
                CustomerRepositoryImpl::extractCustomer,
                id
        );
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
