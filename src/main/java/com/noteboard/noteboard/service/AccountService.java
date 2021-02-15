package com.noteboard.noteboard.service;

import com.noteboard.noteboard.adapter.AccountAdapter;
import com.noteboard.noteboard.entity.Account;
import com.noteboard.noteboard.entity.Role;
import com.noteboard.noteboard.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class AccountService implements UserDetailsService {


    private final AccountRepository accountRepository;

    public Long join(Account account){
     //   validateDuplicateAccount(account);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return account.getId();
    }

   /* private void validateDuplicateAccount(Account account) {
        List<Account> accounts = accountRepository.findByName(account.getUsername());
        if(!accounts.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }*/

    private List<Account> findAccounts(){
        return accountRepository.findAll();
    }

    public Account findOne(Long accountId){
        return accountRepository.findById(accountId).get();
    }


    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<Account> userEntityWrapper = accountRepository.findByEmail(userEmail);
        Account account = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin@example.com").equals(userEmail)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.ACCOUNT.getValue()));
        }

    //    return new User(account.getEmail(), account.getPassword(), authorities);
        return new AccountAdapter(account.getUsername(),account.getPassword(),authorities,account);
    }
}
