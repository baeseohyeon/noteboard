package com.noteboard.noteboard.service;


import com.noteboard.noteboard.entity.Account;
import com.noteboard.noteboard.entity.Message;
import com.noteboard.noteboard.repository.AccountRepository;
import com.noteboard.noteboard.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final AccountRepository accountRepository;


    public void saveMessage(Message message){
        messageRepository.save(message);
    }

    public void deleteMessage(Long messageId){
        messageRepository.deleteById(messageId);
    }

    public List<Message> findMessages(){
       return messageRepository.findAll();
    }

    public void addMessage(String from, String to,String content) {
        Message sendMessage = new Message(from, to, content);
        Message recvMessage = new Message(from, to, content);
        Account fromUser = accountRepository.findByUsername(from);
        Account toUser = accountRepository.findByUsername(to);
        sendMessage.setAccount(fromUser);
        recvMessage.setAccount(toUser);
        fromUser.getMessages().add(sendMessage);
        toUser.getMessages().add(recvMessage);
        messageRepository.save(sendMessage);
        messageRepository.save(recvMessage);

    }
}
