package com.waterkong.banking_system.controller;

import com.waterkong.banking_system.dto.CreateAccountRequest;
import com.waterkong.banking_system.model.Account;
import com.waterkong.banking_system.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.waterkong.banking_system.dto.MoneyRequest;

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

    
}
