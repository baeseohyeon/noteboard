package com.noteboard.noteboard.dto;


import com.noteboard.noteboard.entity.Post;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Data
@NoArgsConstructor
public class PostDTO {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public PostDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.writer = post.getWriter();
        this.createdDate=post.getCreatedDate();
        this.createdDate=post.getModifiedDate();
    }
}
