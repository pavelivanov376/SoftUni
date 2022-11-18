package com.pavel.springdataintro.services;

import com.pavel.springdataintro.exceptions.UserNotFoundException;
import com.pavel.springdataintro.exceptions.UsernameAlreadyExists;

import java.math.BigDecimal;

public interface UserService {
    void register(String username, int age, BigDecimal initialAmount) throws UsernameAlreadyExists;

    void addAccount(BigDecimal amount, long id) throws UserNotFoundException;
}
