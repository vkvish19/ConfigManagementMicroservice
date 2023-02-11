package com.github.vkvish19.bankaccountservice.controller;

import com.github.vkvish19.bankaccountservice.service.InvalidAccountBalanceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler
{
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidAccountBalanceException.class)
    public void handleNotFound() {}

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)   // code - 500
    @ExceptionHandler(Exception.class)
    public void handleGeneralError(Exception e)
    {
        log.error("An error occurred processing request", e);
    }
}
