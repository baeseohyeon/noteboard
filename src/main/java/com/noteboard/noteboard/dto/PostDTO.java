package com.noteboard.noteboard.dto;


import com.noteboard.noteboard.entity.Post;
import com.noteboard.noteboard.entity.UploadFile;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<UploadFileDTO> uploadFileDTOList;
    private List<CommentDTO> commentDTOList;

    public PostDTO(Post post) {
        id = post.getId();
        title = post.getTitle();
        content = post.getContent();
        writer = post.getWriter();
        createdDate=post.getCreatedDate();
        modifiedDate=post.getModifiedDate();
        uploadFileDTOList = post.getUploadFiles().stream().map(file -> new UploadFileDTO(file)).collect(Collectors.toList());
        commentDTOList = post.getComments().stream().map(comment -> new CommentDTO(comment)).collect(Collectors.toList());
    }
}
