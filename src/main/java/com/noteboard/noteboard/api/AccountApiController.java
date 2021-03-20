package com.noteboard.noteboard.api;


import com.noteboard.noteboard.dto.AccountDTO;
import com.noteboard.noteboard.entity.Account;
import com.noteboard.noteboard.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AccountApiController {

    private final AccountService accountService;

    @GetMapping("/api/accounts")
    public List<AccountDTO> accounts(){
        List<Account> accounts = accountService.findAll();
        List<AccountDTO> result = accounts.stream().map(account -> new AccountDTO(account)).collect(Collectors.toList());
        return result;
    }
    @PostMapping("/api/accounts")
    public AccountDTO createAccount(@RequestBody Account account){
        Long id = accountService.join(account);
        return new AccountDTO(account);
    }
}
