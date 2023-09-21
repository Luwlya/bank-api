package com.luwlya.bankapi.service.impl;

import com.luwlya.bankapi.dto.account.AccountDto;
import com.luwlya.bankapi.dto.account.CreateAccountRequest;
import com.luwlya.bankapi.model.Account;
import com.luwlya.bankapi.model.AccountStatus;
import com.luwlya.bankapi.model.Currency;
import com.luwlya.bankapi.test.TestAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AccountServiceImplTest {
    private AccountServiceImpl unit;
    private TestAccountRepository testAccountRepository;
    private Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault()); //to encapsulate current time getting

    @BeforeEach
    void setUp() {
        testAccountRepository = new TestAccountRepository();
        unit = new AccountServiceImpl(testAccountRepository, clock);
    }

    @Test
    void createAccount() {
        //given
        CreateAccountRequest request = new CreateAccountRequest("salary", Currency.EUR, new BigDecimal(1000), UUID.randomUUID());
        //when
        AccountDto result = unit.createAccount(request);
        //then
        assertEquals(request.name(), result.name());
        assertEquals(request.currency(), result.currency());
        assertEquals(request.balance(), result.balance());
        assertNotNull(result.id());
        assertEquals(request.customerId(), result.customerId());
        assertEquals(AccountStatus.ACTIVE, result.status());
        assertEquals(OffsetDateTime.now(clock), result.createdAt());
        assertEquals(OffsetDateTime.now(clock), result.updatedAt());
        assertEquals(1, testAccountRepository.inserted.size());
        Account expected = new Account(result.id(),
                request.customerId(),
                request.name(),
                request.balance(),
                request.currency(),
                result.createdAt(),
                result.updatedAt(),
                result.status());
        assertEquals(expected, testAccountRepository.inserted.get(0));
    }

    @Test
    void getAccount() {
        //given
        UUID id = UUID.randomUUID();
        Account account = new Account(
                id,
                UUID.randomUUID(),
                "Account",
                new BigDecimal(1000),
                Currency.EUR,
                OffsetDateTime.now(clock),
                OffsetDateTime.now(clock),
                AccountStatus.ACTIVE);
        //not has a logic of account's type
        testAccountRepository.accountForGet = account;
        //when
        AccountDto res = unit.getAccount(id);
        //then
        assertEquals(id, res.id());//assert result id
        assertEquals(account.customerId(), res.customerId()); //assert result customer id
        assertEquals(account.currency(), res.currency());
        assertEquals(account.balance(), res.balance());
        assertEquals(account.status(), res.status());
        assertEquals(account.createdAt(), res.createdAt());
        assertEquals(account.updatedAt(), res.updatedAt());
    }

    @Test
    void getAllAccounts() {
    }

    @Test
    void updateAccount() {
    }

    @Test
    void deleteAccount() {
    }
}