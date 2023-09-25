package com.luwlya.bankapi.repository.impl;

import com.luwlya.bankapi.exception.CustomerNotFoundException;
import com.luwlya.bankapi.model.Customer;
import com.luwlya.bankapi.model.CustomerStatus;
import com.luwlya.bankapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    private DataSource dataSource;

    @Autowired
    public CustomerRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static Customer extractCustomer(ResultSet rs) throws SQLException {
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
    public void insert(Customer customer) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO customers(id,status, first_name,last_name,email,address,phone,created_at,updated_at) " +
                             "VALUES (?,?,?,?,?,?,?,?,?)");
        ) {
            statement.setObject(1, customer.id());
            statement.setString(2, customer.status().name());
            statement.setString(3, customer.firstName());
            statement.setString(4, customer.lastName());
            statement.setString(5, customer.email());
            statement.setString(6, customer.address());
            statement.setString(7, customer.phone());
            statement.setObject(8, customer.createdAt());
            statement.setObject(9, customer.updatedAt());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer get(UUID id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE id = ?");
        ) {
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new CustomerNotFoundException(id);
            }
            return extractCustomer(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Customer> getAll() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers");
        ) {
            List<Customer> customers = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customers.add(extractCustomer(resultSet));
            }
            return customers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Customer customer) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE customers " +
                     "SET first_name = ?, " +
                     "last_name = ?," +
                     "email = ?," +
                     "address =?," +
                     "phone = ?," +
                     "updated_at = ?" +
                     " WHERE id=?");
        ) {
            statement.setObject(1, customer.firstName());
            statement.setObject(2, customer.lastName());
            statement.setObject(3, customer.email());
            statement.setObject(4, customer.address());
            statement.setObject(5, customer.phone());
            statement.setObject(6, customer.updatedAt());
            statement.setObject(7, customer.id());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(UUID id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE customers SET status = ? WHERE id=? AND status = ?");
        ) {
            statement.setString(1, CustomerStatus.INACTIVE.name());
            statement.setObject(2, id);
            statement.setString(3, CustomerStatus.ACTIVE.name());
            statement.execute();
            return statement.getUpdateCount() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DataSourceUtils.getConnection(dataSource);
    }
}
