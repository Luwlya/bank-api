package com.luwlya.bankapi.controller;

import com.luwlya.bankapi.dto.CreateTransactionRequest;
import com.luwlya.bankapi.dto.TransactionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
public class TransactionController {

    @PostMapping("/transactions")
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody CreateTransactionRequest request){
        TransactionDto dto = new TransactionDto("id01",
                "debit1",
                "credit1",
                "1000",
                "payment",
                OffsetDateTime.now());
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<TransactionDto> getTransaction(@PathVariable String id){
        TransactionDto dto = new TransactionDto(id,
                "debit23",
                "credit12",
                "100",
                "money",
                OffsetDateTime.now());
        return ResponseEntity.ok().body(dto);

//    @GetMapping("/transactions")
//    public ResponseEntity<TransactionListDto> getAllTransactions(){
//            TransactionListDto listDto = new TransactionListDto(List.of());
//            return ResponseEntity.ok().body(listDto);
//        }
    }
}
