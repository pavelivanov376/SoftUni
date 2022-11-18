package com.pavel.springdataintro;

import com.pavel.springdataintro.exceptions.InsufficientFundsException;
import com.pavel.springdataintro.exceptions.UsernameAlreadyExists;
import com.pavel.springdataintro.services.AccountService;
import com.pavel.springdataintro.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final UserService userService;
    private final AccountService accountService;

    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }


    @Override
    public void run(String... args) throws Exception {
        try {
            this.userService.register("Peso", 20, new BigDecimal(1000));
        } catch (UsernameAlreadyExists e) {
            System.out.println(e.getClass().getSimpleName());
        }

        this.userService.addAccount(new BigDecimal(254), 3);

        try {
            this.accountService.withdrawMoney(new BigDecimal(100), 4L);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getClass().getSimpleName());
        }
        this.accountService.transferMoney(new BigDecimal(130), 5L);

        try {
            accountService.transferBetweenAccounts(6L,7L,new BigDecimal(50));
        } catch (InsufficientFundsException e) {
            throw new RuntimeException(e);
        }
    }
}
