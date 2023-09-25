package com.luwlya.bankapi.repository.impl;

import com.luwlya.bankapi.exception.AccountNotFoundException;
import com.luwlya.bankapi.model.Account;
import com.luwlya.bankapi.model.AccountStatus;
import com.luwlya.bankapi.model.Currency;
import com.luwlya.bankapi.repository.AccountRepository;
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
public class AccountRepositoryImpl implements AccountRepository {
    private DataSource dataSource;

    @Autowired
    public AccountRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static Account extractAccount(ResultSet rs) throws SQLException {
        return new Account(rs.getObject("id", UUID.class),
                rs.getObject("customer_id", UUID.class),
                rs.getString("name"),
                rs.getBigDecimal("balance"),
                Currency.valueOf(rs.getString("currency")),
                rs.getObject("created_at", OffsetDateTime.class),
                rs.getObject("updated_at", OffsetDateTime.class),
                AccountStatus.valueOf(rs.getString("status")));
    }

    @Override
    public void insert(Account account) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO accounts(id,customer_id, name,balance,currency,created_at,updated_at, status) " +
                             "VALUES (?,?,?,?,?,?,?,?)");
        ) {
            statement.setObject(1, account.id());
            statement.setObject(2, account.customerId());
            statement.setString(3, account.name());
            statement.setBigDecimal(4, account.balance());
            statement.setObject(5, account.currency().name());
            statement.setObject(6, account.createdAt());
            statement.setObject(7, account.updatedAt());
            statement.setObject(8, account.status().name());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account get(UUID id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM accounts WHERE id = ?");
        ) {
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new AccountNotFoundException(id);
            }
            return extractAccount(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DataSourceUtils.getConnection(dataSource);
    }

    @Override
    public List<Account> getAll() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM accounts");
        ) {
            List<Account> accounts = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                accounts.add(extractAccount(resultSet));
            }
            return accounts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Account account) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE accounts " +
                     "SET balance = ?, " +
                     "updated_at = ?" +
                     " WHERE id=?");
        ) {
            statement.setObject(1, account.balance());
            statement.setObject(2, account.updatedAt());
            statement.setObject(3, account.id());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(UUID id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE accounts SET status = ? WHERE id=? AND status = ?");
        ) {
            statement.setString(1, AccountStatus.INACTIVE.name());
            statement.setObject(2, id);
            statement.setString(3, AccountStatus.ACTIVE.name());
            statement.execute();
            return statement.getUpdateCount() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
