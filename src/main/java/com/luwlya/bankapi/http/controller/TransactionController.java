package com.luwlya.bankapi.http.controller;

import com.luwlya.bankapi.dto.transaction.CreateTransactionRequest;
import com.luwlya.bankapi.dto.transaction.TransactionDto;
import com.luwlya.bankapi.dto.transaction.TransactionListDto;
import com.luwlya.bankapi.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Validated
@Secured("ROLE_ADMIN")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transactions")
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody @Valid CreateTransactionRequest request) {
        TransactionDto dto = transactionService.createTransaction(request);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<TransactionDto> getTransaction(@PathVariable UUID id) {
        TransactionDto dto = transactionService.getTransaction(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/transactions")
    public ResponseEntity<TransactionListDto> getAllTransactions(@RequestParam(required = false) UUID accountId) {
        TransactionListDto listDto;
        if(accountId != null){
            listDto = transactionService.getTransactionByAccountId(accountId);
        } else {
            listDto = transactionService.getAllTransactions();
        }
        return ResponseEntity.ok().body(listDto);
    }
}
