package com.luwlya.bankapi.http.controller;

import com.luwlya.bankapi.dto.CreateTransactionRequest;
import com.luwlya.bankapi.dto.TransactionDto;
import com.luwlya.bankapi.dto.TransactionListDto;
import com.luwlya.bankapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@RestController
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transactions")
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody CreateTransactionRequest request, UUID id) {
        TransactionDto dto = new TransactionDto(id,
                "debit1",
                "credit1",
                "1000",
                "payment",
                OffsetDateTime.now());
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<TransactionDto> getTransaction(@PathVariable UUID id) {
        TransactionDto dto = new TransactionDto(id,
                "debit23",
                "credit12",
                "100",
                "money",
                OffsetDateTime.now());
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/transactions")
    public ResponseEntity<TransactionListDto> getAllTransactions() {
        TransactionListDto listDto = new TransactionListDto(List.of());
        return ResponseEntity.ok().body(listDto);
    }
}
