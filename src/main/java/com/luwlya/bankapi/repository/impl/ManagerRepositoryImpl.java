package com.luwlya.bankapi.repository.impl;

import com.luwlya.bankapi.exception.ManagerNotFoundException;
import com.luwlya.bankapi.model.Manager;
import com.luwlya.bankapi.model.ManagerStatus;
import com.luwlya.bankapi.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ManagerRepositoryImpl implements ManagerRepository {
    private DataSource dataSource;

    @Autowired
    public ManagerRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static Manager extractManager(ResultSet rs) throws SQLException {
        return new Manager(rs.getObject("id", UUID.class),
                ManagerStatus.valueOf(rs.getString("status")),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getObject("created_at", OffsetDateTime.class),
                rs.getObject("updated_at", OffsetDateTime.class));
    }

    @Override
    public void insert(Manager manager) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO managers(id,status, first_name,last_name,email,password,created_at,updated_at) " +
                             "VALUES (?,?,?,?,?,?,?,?)");
        ) {
            statement.setObject(1, manager.id());
            statement.setString(2, manager.status().name());
            statement.setString(3, manager.firstName());
            statement.setString(4, manager.lastName());
            statement.setString(5, manager.email());
            statement.setString(6, manager.passwordHash());
            statement.setObject(7, manager.createdAt());
            statement.setObject(8, manager.updatedAt());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Manager get(UUID id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM managers WHERE id = ?");
        ) {
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new ManagerNotFoundException(id);
            }
            return extractManager(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Manager> getAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM managers");
        ) {
            List<Manager> managers = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                managers.add(extractManager(resultSet));
            }
            return managers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Manager manager) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE managers " +
                     "SET first_name = ?, " +
                     "last_name = ?," +
                     "email = ?," +
                     "password =?," +
                     "updated_at = ?" +
                     " WHERE id=?");
        ) {
            statement.setObject(1, manager.firstName());
            statement.setObject(2, manager.lastName());
            statement.setObject(3, manager.email());
            statement.setObject(4, manager.passwordHash());
            statement.setObject(5, manager.updatedAt());
            statement.setObject(6, manager.id());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(UUID id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE managers SET status = ? WHERE id=? AND status = ?");
        ) {
            statement.setString(1, ManagerStatus.INACTIVE.name());
            statement.setObject(2, id);
            statement.setString(3, ManagerStatus.ACTIVE.name());
            statement.execute();
            return statement.getUpdateCount() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
