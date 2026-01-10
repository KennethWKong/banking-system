package com.waterkong.banking_system.exception;

public class NotEnoughFundsException extends RuntimeException {
    public NotEnoughFundsException(String msg) {
        super(msg);
    }
}
