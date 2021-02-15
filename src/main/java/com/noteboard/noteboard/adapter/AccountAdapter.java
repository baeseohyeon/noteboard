package com.noteboard.noteboard.adapter;

import com.noteboard.noteboard.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AccountAdapter extends User {

    private Account account;


    public AccountAdapter(String username, String password, Collection<? extends GrantedAuthority> authorities, Account account) {
        super(username, password, authorities);
        this.account=account;
    }

    public Account getAccount(){
        return account;
    }

}
