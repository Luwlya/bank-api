package com.luwlya.bankapi.test;

import com.luwlya.bankapi.model.Account;
import com.luwlya.bankapi.repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestAccountRepository implements AccountRepository {
    public List<Account> inserted = new ArrayList<>();
    public Account accountForGet;
    public List<Account> accountsForGetAll;
    public List<Account> updated = new ArrayList<>();
    public List<UUID> deleted = new ArrayList<>();
    public boolean deleteResult;

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
        return accountsForGetAll;
    }

    @Override
    public void update(Account account) {
        updated.add(account);
    }

    @Override
    public boolean delete(UUID id) {
        deleted.add(id);
        return deleteResult;
    }
}
