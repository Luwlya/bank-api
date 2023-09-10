package com.luwlya.bankapi.repository.impl;

import com.luwlya.bankapi.exception.TransactionNotFoundException;
import com.luwlya.bankapi.model.Transaction;
import com.luwlya.bankapi.repository.TransactionRepository;
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
public class TransactionRepositoryImpl implements TransactionRepository {
    private DataSource dataSource;

    @Autowired
    public TransactionRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static Transaction extractTransaction(ResultSet rs) throws SQLException {
        return new Transaction(rs.getObject("id", UUID.class),
                rs.getObject("debit_account_id", UUID.class),
                rs.getObject("credit_account_id", UUID.class),
                rs.getBigDecimal("amount"),
                rs.getString("description"),
                rs.getObject("created_at", OffsetDateTime.class));
    }

    @Override
    public void insert(Transaction transaction) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO transactions(id,debit_account_id, credit_account_id, amount, description, created_at) " +
                             "VALUES (?,?,?,?,?,?)");
        ) {
            statement.setObject(1, transaction.id());
            statement.setObject(2, transaction.debitAccountId());
            statement.setObject(3, transaction.creditAccountId());
            statement.setBigDecimal(4, transaction.amount());
            statement.setString(5,transaction.description());
            statement.setObject(6, transaction.createdAt());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Transaction get(UUID id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM transactions WHERE id = ?");
        ) {
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new TransactionNotFoundException(id);
            }
            return extractTransaction(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Transaction> getByAccountId(UUID accountId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM transactions WHERE debit_account_id = ? OR credit_account_id = ?"
             );
        ) {
            statement.setObject(1, accountId);
            statement.setObject(2, accountId);
            List<Transaction> transactions = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                transactions.add(extractTransaction(resultSet));
            }
            return transactions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Transaction> getAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM transactions");
        ) {
            List<Transaction> transactions = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                transactions.add(extractTransaction(resultSet));
            }
            return transactions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
