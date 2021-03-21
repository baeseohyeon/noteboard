package com.noteboard.noteboard.service;


import com.noteboard.noteboard.entity.Account;
import com.noteboard.noteboard.entity.Letter;
import com.noteboard.noteboard.repository.AccountRepository;
import com.noteboard.noteboard.repository.LetterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LetterService {

    private final LetterRepository letterRepository;
    private final AccountRepository accountRepository;

    public void deleteLetter(Letter letter){
        letterRepository.delete(letter);
    }
    public List<Letter> findAll(){
        return letterRepository.findAll();
    }

    public void addLetter(String fromAccount, String toAccount, String content){
        Account from = accountRepository.findByUsername(fromAccount);
        Account to = accountRepository.findByUsername(toAccount);
        Letter sendLetter = new Letter(fromAccount,toAccount,content,from);
        Letter recvLetter = new Letter(fromAccount,toAccount,content,to);
        letterRepository.save(sendLetter);
        letterRepository.save(recvLetter);
    }
}
