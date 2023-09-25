package com.luwlya.bankapi.service.impl;

import com.luwlya.bankapi.dto.account.AccountDto;
import com.luwlya.bankapi.dto.account.AccountsListDto;
import com.luwlya.bankapi.dto.account.CreateAccountRequest;
import com.luwlya.bankapi.dto.account.UpdateAccountRequest;
import com.luwlya.bankapi.exception.AccountNotFoundException;
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
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplTest {
    //what we are testing as a unit from production code
    private AccountServiceImpl unit;
    //test implementation of dependencies; interface has numerous implementations
    private TestAccountRepository testAccountRepository;
    //to encapsulate current time getting
    private Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

    @BeforeEach
    void setUp() {
        testAccountRepository = new TestAccountRepository();
        //instance
        unit = new AccountServiceImpl(testAccountRepository, clock);
    }

    @Test
    void createAccount() {
        //given
        CreateAccountRequest request = new CreateAccountRequest("salary", Currency.EUR, new BigDecimal(1000), UUID.randomUUID());
        //when
        AccountDto result = unit.createAccount(request);
        //then
        //what the method returns
        assertEquals(request.name(), result.name());
        assertEquals(request.currency(), result.currency());
        assertEquals(request.balance(), result.balance());
        assertNotNull(result.id());
        assertEquals(request.customerId(), result.customerId());
        assertEquals(AccountStatus.ACTIVE, result.status());
        assertEquals(OffsetDateTime.now(clock), result.createdAt());
        assertEquals(OffsetDateTime.now(clock), result.updatedAt());
        //what the method calls from repo
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
        //given
        Account account = new Account(
                UUID.randomUUID(),
                UUID.randomUUID(),
                "Account",
                new BigDecimal(1000),
                Currency.EUR,
                OffsetDateTime.now(clock),
                OffsetDateTime.now(clock),
                AccountStatus.ACTIVE);
        testAccountRepository.accountsForGetAll = List.of(account);
        //when
        AccountsListDto res = unit.getAllAccounts();
        //then
        assertNotNull(res);
        assertEquals(1, res.items().size());
        assertEquals(account.id(), res.items().get(0).id());//assert result id
        assertEquals(account.customerId(), res.items().get(0).customerId()); //assert result customer id
    }

    @Test
    void updateAccount() {
        //given
        Account account = new Account(
                UUID.randomUUID(),
                UUID.randomUUID(),
                "Account",
                new BigDecimal(1000),
                Currency.EUR,
                OffsetDateTime.now(clock),
                OffsetDateTime.now(clock),
                AccountStatus.ACTIVE);
        testAccountRepository.accountForGet = account;
        BigDecimal newBalance = new BigDecimal(2000);
        //when
        AccountDto res = unit.updateAccount(account.id(), new UpdateAccountRequest(newBalance));
        //then
        assertNotNull(res);
        assertEquals(account.id(), res.id());
        assertEquals(newBalance, res.balance());
        assertEquals(1, testAccountRepository.updated.size());
        assertEquals(account.id(), testAccountRepository.updated.get(0).id());
        assertEquals(newBalance, testAccountRepository.updated.get(0).balance());
    }

    @Test
    void deleteAccount() {
        //given
        UUID id = UUID.randomUUID();
        testAccountRepository.deleteResult = true;
        //when
        unit.deleteAccount(id);
        //then
        assertEquals(1, testAccountRepository.deleted.size());
        assertEquals(id, testAccountRepository.deleted.get(0));
    }

    @Test
    void deleteNonExistedAccount() {
        //given
        UUID id = UUID.randomUUID();
        testAccountRepository.deleteResult = false;
        boolean exceptionWasThrown = false;
        //when
        try {
            unit.deleteAccount(id);
        } catch (AccountNotFoundException e) {
            exceptionWasThrown = true;
        }
        //then
        assertEquals(1, testAccountRepository.deleted.size());
        assertEquals(id, testAccountRepository.deleted.get(0));
        assertTrue(exceptionWasThrown);
    }
}