package com.noteboard.noteboard.dto;

import com.noteboard.noteboard.entity.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {



    private Long id;
    private String username;
    private String Email;

    public AccountDTO(Account account){
        id=account.getId();
        username=account.getUsername();
        Email=account.getEmail();
    }
}
