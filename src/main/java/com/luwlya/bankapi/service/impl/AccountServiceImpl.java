package com.luwlya.bankapi.service.impl;

import com.luwlya.bankapi.dto.account.AccountDto;
import com.luwlya.bankapi.dto.account.AccountsListDto;
import com.luwlya.bankapi.dto.account.CreateAccountRequest;
import com.luwlya.bankapi.dto.account.UpdateAccountRequest;
import com.luwlya.bankapi.exception.AccountNotFoundException;
import com.luwlya.bankapi.model.Account;
import com.luwlya.bankapi.model.AccountStatus;
import com.luwlya.bankapi.repository.AccountRepository;
import com.luwlya.bankapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(CreateAccountRequest request) {
        Account account = new Account(
                UUID.randomUUID(),
                request.customerId(),
                request.name(),
                request.balance(),
                request.currency(),
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                AccountStatus.ACTIVE);
        accountRepository.insert(account);
        return dto(account);
    }

    private AccountDto dto(Account account) {
        return new AccountDto(
                account.id(),
                account.customerId(),
                account.name(),
                account.balance(),
                account.currency(),
                account.createdAt(),
                account.updatedAt(),
                AccountStatus.ACTIVE);
    }

    @Override
    public AccountDto getAccount(UUID id) {
        Account account = accountRepository.get(id);
        return dto(account);
    }

    @Override
    public AccountsListDto getAllAccounts() {
        List<Account> accounts = accountRepository.getAll();
        List<AccountDto> result = accounts.stream().map(this::dto).toList();
        return new AccountsListDto(result);
    }

    @Override
    public AccountDto updateAccount(UUID id, UpdateAccountRequest update) {
        Account account = accountRepository.get(id);
        Account updateAccount = new Account(id,
                account.customerId(),
                account.name(),
                update.balance() != null ? update.balance() : account.balance(),
                account.currency(),
                account.createdAt(),
                OffsetDateTime.now(),
                AccountStatus.ACTIVE);
        accountRepository.update(updateAccount);
        return dto(updateAccount);
    }

    @Override
    public void deleteAccount(UUID id) {
        boolean deleted = accountRepository.delete(id);
        if (deleted) {
            System.out.println("Account " + id + " has been deleted");
        } else {
            throw new AccountNotFoundException(id);
        }
    }
}
