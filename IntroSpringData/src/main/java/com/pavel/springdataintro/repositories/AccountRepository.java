package com.pavel.springdataintro.repositories;

import com.pavel.springdataintro.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
