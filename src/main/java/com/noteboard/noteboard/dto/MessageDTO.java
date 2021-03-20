package com.noteboard.noteboard.dto;


import com.noteboard.noteboard.entity.Message;
import lombok.Data;

@Data
public class MessageDTO {

    Long id;
    String from;
    String to;
    String content;
    public MessageDTO(Message message){
        id=message.getId();
        from=message.getFrom();
        to=message.getTo();
        content=message.getContent();
    }
}
