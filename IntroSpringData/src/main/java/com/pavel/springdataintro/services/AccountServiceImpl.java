package com.pavel.springdataintro.services;

import com.pavel.springdataintro.exceptions.InsufficientFundsException;
import com.pavel.springdataintro.models.Account;
import com.pavel.springdataintro.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public void withdrawMoney(BigDecimal amount, Long id) throws InsufficientFundsException {
        Account account = getAccount(id);
        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException();
        }

        account.setBalance(account.getBalance().subtract(amount));
        this.accountRepository.save(account);
    }

    @Override
    public void transferMoney(BigDecimal amount, Long id) {
        Account account = getAccount(id);

        account.setBalance(account.getBalance().add(amount));
        this.accountRepository.save(account);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public void transferBetweenAccounts(Long formId, Long toId, BigDecimal amount) throws InsufficientFundsException {
        withdrawMoney(amount, formId);
        transferMoney(amount,toId);
    }

    private Account getAccount(Long id) {
        var account = accountRepository.findById(id).orElseThrow();
        return account;
    }
}
