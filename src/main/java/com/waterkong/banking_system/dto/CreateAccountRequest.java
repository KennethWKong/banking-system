package com.waterkong.banking_system.dto;

import jakarta.validation.constraints.NotBlank;

// Represents the JSON body when creating a new account.
public class CreateAccountRequest {

    @NotBlank
    private String ownerName;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
