package com.noteboard.noteboard.dto;

import com.noteboard.noteboard.entity.Comment;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class CommentDTO {

    private Long id;
    private String writer;
    private String content;

    public CommentDTO(Comment comment){
        id=comment.getId();
        writer=comment.getWriter();
        content=comment.getContent();
    }
}
