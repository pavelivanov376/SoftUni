package com.pavel.webapp.repositories;

import com.pavel.webapp.entities.PersonAccount;
import com.pavel.webapp.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonAccountRepository extends JpaRepository<PersonAccount, Long> {
    boolean existsByUsernameOrEmail(String username, String email);

    PersonAccount findFirstByUsername(String username);
}
