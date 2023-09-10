package com.luwlya.bankapi.repository;

import com.luwlya.bankapi.model.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository {
    void insert(Transaction transaction);

    Transaction get(UUID id);

    List<Transaction> getAll();
}
