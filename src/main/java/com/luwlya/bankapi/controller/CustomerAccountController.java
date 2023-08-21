package com.luwlya.bankapi.controller;

import com.luwlya.bankapi.dto.CreateCustomerAccountRequest;
import com.luwlya.bankapi.dto.CustomerAccountDto;
import com.luwlya.bankapi.dto.CustomerAccountsListDto;
import com.luwlya.bankapi.dto.UpdateCustomerAccountRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
public class CustomerAccountController {
    @PostMapping("/customer-accounts")
    public ResponseEntity<CustomerAccountDto> createCustomerAccount(@RequestBody CreateCustomerAccountRequest request) {
        CustomerAccountDto customerAccount = new CustomerAccountDto("id1",
                "customerId12",
                "name",
                request.balance(),
                request.currency(),
                OffsetDateTime.now(),
                OffsetDateTime.now());
        return ResponseEntity.ok().body(customerAccount);
    }

    @GetMapping("/customer-accounts")
    public ResponseEntity<CustomerAccountsListDto> getAllCustomerAccounts() {
        return ResponseEntity.ok().body(new CustomerAccountsListDto(List.of()));
    }

    @GetMapping("/customer-accounts/{id}")
    public ResponseEntity<CustomerAccountDto> getCustomerAccount(@PathVariable String id) {
        CustomerAccountDto accountDto = new CustomerAccountDto(id,
                "001",
                "Stepan Bandera",
                "1000",
                "EUR",
                OffsetDateTime.now(),
                OffsetDateTime.now());
        return ResponseEntity.ok().body(accountDto);
    }

    @PatchMapping("/customer-accounts/{id}")
    public ResponseEntity<CustomerAccountDto> updateCustomerAccount(@PathVariable String id,
                                                                    @RequestBody UpdateCustomerAccountRequest update) {
        CustomerAccountDto accountDto = new CustomerAccountDto(id,
                "007",
                "Zalujnii",
                update.balance(),
                update.currency(),
                OffsetDateTime.now(),
                OffsetDateTime.now());
        return ResponseEntity.ok().body(accountDto);
    }

    @DeleteMapping("/customer-accounts/{id}")
    public ResponseEntity<CustomerAccountDto> deleteCustomerAccount(@PathVariable String id){
        System.out.println("Customer account " + id + " has been deleted");
        return ResponseEntity.noContent().build();
    }
}
