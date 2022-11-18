package com.pavel.springdataintro.services;

import com.pavel.springdataintro.exceptions.UserNotFoundException;
import com.pavel.springdataintro.exceptions.UsernameAlreadyExists;
import com.pavel.springdataintro.models.Account;
import com.pavel.springdataintro.models.User;
import com.pavel.springdataintro.repositories.AccountRepository;
import com.pavel.springdataintro.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public UserServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void register(String username, int age, BigDecimal initialAmount) throws UsernameAlreadyExists {
      if (this.userRepository.existsByUsername(username)) {
          throw new UsernameAlreadyExists();
      }

        var user = new User();
        user.setAge(age);
        user.setUsername(username);

        this.userRepository.save(user);

        createAccount(initialAmount, user);
    }

    private void createAccount(BigDecimal initialAmount, User user) {
        Account account = new Account();
        account.setBalance(initialAmount);
        account.setUser(user);

        this.accountRepository.save(account);
    }

    @Override
    public void addAccount(BigDecimal amount, long id) throws UserNotFoundException {
        User user = this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        createAccount(amount, user);
    }
}
