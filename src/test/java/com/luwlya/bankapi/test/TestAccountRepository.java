package com.luwlya.bankapi.test;

import com.luwlya.bankapi.model.Account;
import com.luwlya.bankapi.repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestAccountRepository implements AccountRepository {
    public List<Account> inserted = new ArrayList<>();
    public Account accountForGet;

    @Override
    public void insert(Account account) {
        inserted.add(account);
    }

    @Override
    public Account get(UUID id) {
        return accountForGet.withId(id);
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
