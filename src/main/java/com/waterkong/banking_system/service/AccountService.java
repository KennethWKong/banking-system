package com.waterkong.banking_system.service;

import com.waterkong.banking_system.model.Account;
import com.waterkong.banking_system.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import java.math.BigDecimal;

// Holds the core logic for working with accounts.
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    // Spring injects an AccountRepository here automatically.
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Create a new account with a starting balance of 0.
    public Account createAccount(String ownerName) {
        Account account = new Account(ownerName);
        return accountRepository.save(account);
    }

    // Look up a single account, or throw if it doesn't exist.
    public Account getAccount(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found: " + id));
    }

    // Return all accounts.
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

        // Add money to an account
    public Account deposit(Long id, BigDecimal amount) {
        Account account = getAccount(id); // Find account or error
        account.setBalance(account.getBalance().add(amount)); // Add funds
        return accountRepository.save(account); // Persist to DB
    }

    // Remove money from an account
    public Account withdraw(Long id, BigDecimal amount) {
        Account account = getAccount(id);
        BigDecimal newBalance = account.getBalance().subtract(amount);

        // Do not allow negative balance (business rule)
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        account.setBalance(newBalance);
        return accountRepository.save(account);
    }

    

}
