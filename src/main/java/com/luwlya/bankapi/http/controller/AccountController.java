package com.luwlya.bankapi.http.controller;

import com.luwlya.bankapi.dto.account.AccountDto;
import com.luwlya.bankapi.dto.account.AccountsListDto;
import com.luwlya.bankapi.dto.account.CreateAccountRequest;
import com.luwlya.bankapi.dto.account.UpdateAccountRequest;
import com.luwlya.bankapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Validated
@Secured("ROLE_ADMIN")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/accounts")
    public ResponseEntity<AccountDto> createAccount(@RequestBody CreateAccountRequest request) {
        AccountDto customerAccount = accountService.createAccount(request);
        return ResponseEntity.ok().body(customerAccount);
    }

    @GetMapping("/accounts")
    public ResponseEntity<AccountsListDto> getAllAccounts() {
        AccountsListDto dto = accountService.getAllAccounts();
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable UUID id) {
        AccountDto accountDto = accountService.getAccount(id);
        return ResponseEntity.ok().body(accountDto);
    }

    @PatchMapping("/accounts/{id}")
    public ResponseEntity<AccountDto> updateCustomerAccount(@PathVariable UUID id,
                                                            @RequestBody UpdateAccountRequest update) {
        AccountDto accountDto = accountService.updateAccount(id, update);
        return ResponseEntity.ok().body(accountDto);
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<AccountDto> deleteAccount(@PathVariable UUID id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
