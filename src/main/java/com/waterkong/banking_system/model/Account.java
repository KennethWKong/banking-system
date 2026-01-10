package com.waterkong.banking_system.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

// This class represents a bank account in both Java AND the database.
@Entity
@Table(name = "accounts")
public class Account {

    // Primary key column (id) that auto-increments.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Owner's name – required (nullable = false).
    @Column(nullable = false)
    private String ownerName;

    // Account balance, using BigDecimal to avoid floating point issues.
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;

    // When the account was created.
    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    // JPA needs a no-args constructor.
    public Account() {
    }

    public Account(String ownerName) {
        this.ownerName = ownerName;
        this.balance = BigDecimal.ZERO;
        this.createdAt = Instant.now();
    }

    // Getters and setters (needed for JPA and JSON serialization).

    public Long getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
