package com.luwlya.bankapi.service.impl;

import com.luwlya.bankapi.dto.transaction.CreateTransactionRequest;
import com.luwlya.bankapi.dto.transaction.TransactionDto;
import com.luwlya.bankapi.dto.transaction.TransactionListDto;
import com.luwlya.bankapi.exception.AccountNotFoundException;
import com.luwlya.bankapi.exception.InsufficientBalanceException;
import com.luwlya.bankapi.model.Account;
import com.luwlya.bankapi.model.Transaction;
import com.luwlya.bankapi.repository.AccountRepository;
import com.luwlya.bankapi.repository.TransactionRepository;
import com.luwlya.bankapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public TransactionDto createTransaction(CreateTransactionRequest request) {
        Account creditAccount = accountRepository.get(request.creditAccountId());
        Account debitAccount = accountRepository.get(request.debitAccountId());
        if (creditAccount == null) {
            throw new AccountNotFoundException(request.creditAccountId());
        }
        if (debitAccount == null) {
            throw new AccountNotFoundException(request.debitAccountId());
        }
        if (creditAccount.balance().compareTo(request.amount()) < 0) {
            throw new InsufficientBalanceException(creditAccount.id());
        }
        Account updatedCreditAccount = creditAccount.withBalance(creditAccount.balance().subtract(request.amount()));
        Account updatedDebitAccount = debitAccount.withBalance(debitAccount.balance().add(request.amount()));
        Transaction transaction = new Transaction(
                UUID.randomUUID(),
                request.debitAccountId(),
                request.creditAccountId(),
                request.amount(),
                request.description(),
                OffsetDateTime.now());
        transactionRepository.insert(transaction);
        accountRepository.update(updatedCreditAccount);
        accountRepository.update(updatedDebitAccount);
        return dto(transaction);
    }

    private TransactionDto dto(Transaction transaction) {
        return new TransactionDto(
                transaction.id(),
                transaction.debitAccountId(),
                transaction.creditAccountId(),
                transaction.amount(),
                transaction.description(),
                transaction.createdAt());
    }

    @Override
    public TransactionDto getTransaction(UUID id) {
        Transaction transaction = transactionRepository.get(id);
        return dto(transaction);
    }

    @Override
    public TransactionListDto getAllTransactions() {
        List<Transaction> transactions = transactionRepository.getAll();
        List<TransactionDto> result = transactions.stream().map(this::dto).toList();
        return new TransactionListDto(result);
    }

    @Override
    public TransactionListDto getTransactionByAccountId(UUID accountId) {
        List<Transaction> transactions = transactionRepository.getByAccountId(accountId);
        List<TransactionDto> result = transactions.stream().map(this::dto).toList();
        return new TransactionListDto(result);
    }
}
