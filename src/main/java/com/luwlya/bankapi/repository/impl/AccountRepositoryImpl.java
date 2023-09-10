package com.luwlya.bankapi.repository.impl;

import com.luwlya.bankapi.model.Account;
import com.luwlya.bankapi.repository.AccountRepository;

import java.util.List;
import java.util.UUID;

public class AccountRepositoryImpl implements AccountRepository {
    @Override
    public void insert(Account account) {

    }

    @Override
    public Account get(UUID id) {
        return null;
    }

    @Override
    public List<Account> getAll() {
        return null;
    }

    @Override
    public void update(Account account) {

    }

    @Override
    public boolean delete(UUID id) {
        return false;
    }
}
