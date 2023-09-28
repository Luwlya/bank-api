package com.luwlya.bankapi.repository.impl;

import com.luwlya.bankapi.exception.CustomerNotFoundException;
import com.luwlya.bankapi.model.Customer;
import com.luwlya.bankapi.model.CustomerStatus;
import com.luwlya.bankapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    private Customer extractCustomer(ResultSet rs, int rowNum) throws SQLException {
        return new Customer(rs.getObject("id", UUID.class),
                CustomerStatus.valueOf(rs.getString("status")),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("address"),
                rs.getString("phone"),
                rs.getObject("created_at", OffsetDateTime.class),
                rs.getObject("updated_at", OffsetDateTime.class));
    }

    @Override
    public void insert(Customer customer) throws SQLException {
        jdbcTemplate.update(
                "INSERT INTO customers(id,status, first_name,last_name,email,address,phone,created_at,updated_at) " +
                        "VALUES (?,?,?,?,?,?,?,?,?)",
                customer.id(),
                customer.status().name(),
                customer.firstName(),
                customer.lastName(),
                customer.email(),
                customer.address(),
                customer.phone(),
                customer.createdAt(),
                customer.updatedAt()
        );
    }

    @Override
    public Customer get(UUID id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM customers WHERE id = ?",
                    this::extractCustomer,
                    id
            );
        } catch (EmptyResultDataAccessException e) {
            throw new CustomerNotFoundException(id);
        }
    }

    @Override
    public List<Customer> getAll() {
        return jdbcTemplate.query("SELECT * FROM customers", this::extractCustomer);
    }

    @Override
    public void update(Customer customer) {
        jdbcTemplate.update(
                "UPDATE customers SET first_name = ?, last_name = ?,email = ?,address =?,phone = ?,updated_at = ? WHERE id=?",
                customer.firstName(),
                customer.lastName(),
                customer.email(),
                customer.address(),
                customer.phone(),
                customer.updatedAt(),
                customer.id()
        );
    }

    @Override
    public boolean delete(UUID id) {
        int updated = jdbcTemplate.update("UPDATE customers SET status = ? WHERE id=? AND status = ?",
                CustomerStatus.INACTIVE.name(), id, CustomerStatus.ACTIVE.name());
        return updated > 0;
    }
}
