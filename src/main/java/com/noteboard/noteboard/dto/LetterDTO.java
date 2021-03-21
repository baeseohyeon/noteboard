package com.noteboard.noteboard.dto;


import com.noteboard.noteboard.entity.Letter;
import lombok.Data;

@Data
public class LetterDTO {

    Long id;
    String from;
    String to;
    String content;
    public LetterDTO(Letter letter){
        id=letter.getId();
        from=letter.getFromAccount();
        to=letter.getToAccount();
        content=letter.getContent();
    }
}
