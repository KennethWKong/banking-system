package com.waterkong.banking_system.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

// Represents JSON like { "amount": 50.00 }
public class MoneyRequest {
// JSON MUST include "amount"
    @NotNull     
    // Amount must be >= 0.01             
    @DecimalMin("0.01")       
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
