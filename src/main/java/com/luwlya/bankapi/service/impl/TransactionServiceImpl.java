package com.luwlya.bankapi.service.impl;

import com.luwlya.bankapi.dto.transaction.CreateTransactionRequest;
import com.luwlya.bankapi.dto.transaction.TransactionDto;
import com.luwlya.bankapi.dto.transaction.TransactionListDto;
import com.luwlya.bankapi.repository.AccountRepository;
import com.luwlya.bankapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    private AccountRepository accountRepository;

    @Autowired
    public TransactionServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public TransactionDto createTransaction(CreateTransactionRequest request) {

        return null;
    }

    @Override
    public TransactionDto getTransaction(UUID id) {
        return null;
    }

    @Override
    public TransactionListDto getAllTransactions() {
        return null;
    }

    @Override
    public TransactionListDto getTransactionByAccountId(UUID accountId) {
        return null;
    }
}
