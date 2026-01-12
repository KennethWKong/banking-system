package com.waterkong.banking_system.controller;

import com.waterkong.banking_system.dto.CreateAccountRequest;
import com.waterkong.banking_system.model.Account;
import com.waterkong.banking_system.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.waterkong.banking_system.dto.MoneyRequest;

import com.waterkong.banking_system.dto.TransferRequest;

import com.waterkong.banking_system.model.Transaction;


@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // POST /accounts
    // Body: { "ownerName": "Kenneth" }
    @PostMapping
    public Account create(@RequestBody CreateAccountRequest request) {
        return accountService.createAccount(request.getOwnerName());
    }

    // GET /accounts/{id}
    @GetMapping("/{id}")
    public Account getOne(@PathVariable Long id) {
        return accountService.getAccount(id);
    }

    // GET /accounts
    @GetMapping
    public List<Account> getAll() {
        return accountService.getAllAccounts();
    }

        // POST /accounts/{id}/deposit
    @PostMapping("/{id}/deposit")
    public Account deposit(
            @PathVariable Long id,
            @RequestBody MoneyRequest request) {
        return accountService.deposit(id, request.getAmount());
    }

    // POST /accounts/{id}/withdraw
    @PostMapping("/{id}/withdraw")
    public Account withdraw(
            @PathVariable Long id,
            @RequestBody MoneyRequest request) {
        return accountService.withdraw(id, request.getAmount());
    }

    // POST / accounts/transfer
    @PostMapping("/transfer")
    public Account transfer(@RequestBody TransferRequest request) {
        return accountService.transfer(
                request.getFromId(),
                request.getToId(),
                request.getAmount()
        );
    }

    // GET /accounts/{id}/transactions
    @GetMapping("/accounts/{id}/transactions")
    public List<Transaction> getTransactions(@PathVariable Long id) {
        return accountService.getTransactionsForAccount(id);
    }

}
