package com.luwlya.bankapi.repository;

import com.luwlya.bankapi.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.OffsetDateTime;
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
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO customers(id,first_name,last_name,email,address,phone,created_at,updated_at) " +
                             "VALUES (?,?,?,?,?,?,?,?)");
        ) {
            statement.setObject(1, customer.id());
            statement.setString(2, customer.firstName());
            statement.setString(3, customer.lastName());
            statement.setString(4, customer.email());
            statement.setString(5, customer.address());
            statement.setString(6, customer.phone());
            statement.setObject(7, customer.createdAt());
            statement.setObject(8, customer.updatedAt());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer get(UUID id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE id = ?");
        ) {
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();
            return extractCustomer(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
