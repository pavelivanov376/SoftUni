package com.pavel.springdataintro.services;

import com.pavel.springdataintro.exceptions.InsufficientFundsException;

import java.math.BigDecimal;

public interface AccountService {
    void withdrawMoney(BigDecimal amount, Long id) throws InsufficientFundsException;

    void transferMoney(BigDecimal amount, Long id);

    void transferBetweenAccounts(Long formId, Long toId, BigDecimal amount) throws InsufficientFundsException;
}
