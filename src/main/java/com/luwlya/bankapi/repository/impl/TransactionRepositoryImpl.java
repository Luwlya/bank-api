package com.luwlya.bankapi.repository.impl;

import com.luwlya.bankapi.model.Transaction;
import com.luwlya.bankapi.repository.TransactionRepository;

import java.util.List;
import java.util.UUID;

public class TransactionRepositoryImpl implements TransactionRepository {
    @Override
    public void insert(Transaction transaction) {

    }

    @Override
    public Transaction get(UUID id) {
        return null;
    }

    @Override
    public List<Transaction> getAll() {
        return null;
    }
}
