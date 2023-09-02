package com.luwlya.bankapi.service;

import com.luwlya.bankapi.dto.CreateTransactionRequest;
import com.luwlya.bankapi.dto.TransactionDto;
import com.luwlya.bankapi.dto.TransactionListDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {

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
}
