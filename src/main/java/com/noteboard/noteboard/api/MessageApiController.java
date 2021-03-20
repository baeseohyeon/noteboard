package com.noteboard.noteboard.api;


import com.noteboard.noteboard.dto.MessageDTO;
import com.noteboard.noteboard.entity.Message;
import com.noteboard.noteboard.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MessageApiController {

    private final MessageService messageService;


    public List<MessageDTO> messagesv1(){
        List<Message> messages = messageService.findMessages();
        List<MessageDTO> result = messages.stream().map(message -> new MessageDTO(message)).collect(Collectors.toList());
        return result;
    }
}
