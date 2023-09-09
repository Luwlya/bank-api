package com.luwlya.bankapi.http.controller;

import com.luwlya.bankapi.dto.account.CreateCustomerAccountRequest;
import com.luwlya.bankapi.dto.account.CustomerAccountDto;
import com.luwlya.bankapi.dto.account.CustomerAccountsListDto;
import com.luwlya.bankapi.dto.account.UpdateCustomerAccountRequest;
import com.luwlya.bankapi.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@Secured("ROLE_ADMIN")
public class CustomerAccountController {

    private CustomerAccountService customerAccountService;

    @Autowired
    public CustomerAccountController(CustomerAccountService customerAccountService) {
        this.customerAccountService = customerAccountService;
    }

    @PostMapping("/customer-accounts")
    public ResponseEntity<CustomerAccountDto> createCustomerAccount(@RequestBody CreateCustomerAccountRequest request) {
        CustomerAccountDto customerAccount = customerAccountService.createCustomerAccount(request);
        return ResponseEntity.ok().body(customerAccount);
    }

    @GetMapping("/customer-accounts")
    public ResponseEntity<CustomerAccountsListDto> getAllCustomerAccounts() {
        CustomerAccountsListDto dto = customerAccountService.getAllCustomerAccounts();
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/customer-accounts/{id}")
    public ResponseEntity<CustomerAccountDto> getCustomerAccount(@PathVariable String id) {
        CustomerAccountDto accountDto = customerAccountService.getCustomerAccount(id);
        return ResponseEntity.ok().body(accountDto);
    }

    @PatchMapping("/customer-accounts/{id}")
    public ResponseEntity<CustomerAccountDto> updateCustomerAccount(@PathVariable String id,
                                                                    @RequestBody UpdateCustomerAccountRequest update) {
        CustomerAccountDto accountDto = customerAccountService.updateCustomerAccount(id, update);
        return ResponseEntity.ok().body(accountDto);
    }

    @DeleteMapping("/customer-accounts/{id}")
    public ResponseEntity<CustomerAccountDto> deleteCustomerAccount(@PathVariable String id) {
        customerAccountService.deleteCustomerAccount(id);
        return ResponseEntity.noContent().build();
    }
}
