package com.waterkong.banking_system.service;

import com.waterkong.banking_system.model.Account;
import com.waterkong.banking_system.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import java.math.BigDecimal;

import com.waterkong.banking_system.exception.AccountNotFoundException;
import com.waterkong.banking_system.exception.NotEnoughFundsException;

import com.waterkong.banking_system.model.Transaction;
import com.waterkong.banking_system.model.TransactionType;
import com.waterkong.banking_system.repository.TransactionRepository;


// Holds the core logic for working with accounts.
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    //added transaction repository
    private final TransactionRepository transactionRepository;
//updated constructor to have transaction repository
    public AccountService(AccountRepository accountRepository,
                        TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }


    // Create a new account with a starting balance of 0.
    public Account createAccount(String ownerName) {
        Account account = new Account(ownerName);
        return accountRepository.save(account);
    }

    // Look up a single account, or throw if it doesn't exist.
    public Account getAccount(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account " + id + " not found"));
    }

    // Return all accounts.
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // Add money to an account
    //update deposit to record transaction
    public Account deposit(Long id, BigDecimal amount) {
        Account account = getAccount(id);

        BigDecimal newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance);
        Account saved = accountRepository.save(account);

        recordTransaction(saved, TransactionType.DEPOSIT, amount, "Deposit");

        return saved;
    }


    // Remove money from an account
    // update withdraw to record transaction
    public Account withdraw(Long id, BigDecimal amount) {
        Account account = getAccount(id);

        BigDecimal newBalance = account.getBalance().subtract(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new NotEnoughFundsException("Insufficient funds in account " + id);
        }

        account.setBalance(newBalance);
        Account saved = accountRepository.save(account);

        recordTransaction(saved, TransactionType.WITHDRAWAL, amount, "Withdrawal");

        return saved;
    }


    //add helper method recordTransaction(...)
    private void recordTransaction(Account account,TransactionType type, BigDecimal amount,String description) {
        Transaction tx = new Transaction(account, type, amount, description);
        transactionRepository.save(tx);
    }


    // Transfer money from one account to another
    // update transfer to record transactions such as in/out
    public Account transfer(Long fromId, Long toId, BigDecimal amount) {
        Account fromAccount = withdraw(fromId, amount);
        Account toAccount = deposit(toId, amount);

        recordTransaction(fromAccount,
                TransactionType.TRANSFER_OUT,
                amount,
                "Transfer to account " + toId);

        recordTransaction(toAccount,
                TransactionType.TRANSFER_IN,
                amount,
                "Transfer from account " + fromId);

        return fromAccount;
    }

    // Get all transactions for a specific account
    public List<Transaction> getTransactionsForAccount(Long accountId) {
        // ensures 404 if account missing
        getAccount(accountId); 
        return transactionRepository.findByAccountIdOrderByCreatedAtDesc(accountId);
    }



}
