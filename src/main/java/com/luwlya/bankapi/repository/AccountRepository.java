package com.luwlya.bankapi.repository;

import com.luwlya.bankapi.model.Account;

import java.util.List;
import java.util.UUID;

public interface AccountRepository {
    void insert(Account account);

    Account get(UUID id);

    List<Account> getAll();

    void update(Account account);

    boolean delete(UUID id);
}
