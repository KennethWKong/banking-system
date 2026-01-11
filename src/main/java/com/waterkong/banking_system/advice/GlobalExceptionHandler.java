package com.waterkong.banking_system.advice;

import com.waterkong.banking_system.exception.AccountNotFoundException;
import com.waterkong.banking_system.exception.NotEnoughFundsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

}