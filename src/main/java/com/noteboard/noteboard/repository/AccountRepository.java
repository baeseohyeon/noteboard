package com.noteboard.noteboard.repository;

import com.noteboard.noteboard.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {

    public Account findByUsername(String username);
    public Account findByUsernameAndPassword(String username, String password);

    Optional<Account> findByEmail(String userEmail);

 //   List<Account> findByName(String username);
}
