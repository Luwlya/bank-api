package com.luwlya.bankapi.service;

import com.luwlya.bankapi.dto.CreateTransactionRequest;
import com.luwlya.bankapi.dto.TransactionDto;
import com.luwlya.bankapi.dto.TransactionListDto;

import java.util.UUID;

public interface TransactionService {
    TransactionDto createTransaction(CreateTransactionRequest request);

    TransactionDto getTransaction(UUID id);

    TransactionListDto getAllTransactions();

}
