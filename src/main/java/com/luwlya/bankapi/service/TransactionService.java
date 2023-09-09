package com.luwlya.bankapi.service;

import com.luwlya.bankapi.dto.transaction.CreateTransactionRequest;
import com.luwlya.bankapi.dto.transaction.TransactionDto;
import com.luwlya.bankapi.dto.transaction.TransactionListDto;

import java.util.UUID;

public interface TransactionService {
    TransactionDto createTransaction(CreateTransactionRequest request);

    TransactionDto getTransaction(UUID id);

    TransactionListDto getAllTransactions();

}
