package com.noteboard.noteboard.controller;


import com.noteboard.noteboard.entity.Account;
import com.noteboard.noteboard.entity.Message;
import com.noteboard.noteboard.service.AccountService;
import com.noteboard.noteboard.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final AccountService accountService;


    @GetMapping("/user/message")
    public String sendMessage(){

        return "board/writemessage.html";

    }

    @PostMapping("/user/message")
    public String sendMessage(Message message, Principal principal){
        String from = principal.getName();
        String to = message.getTo();
        String content = message.getContent();
        messageService.addMessage(from, to, content);
        return "redirect:/";

    }

}
