package com.noteboard.noteboard.api;


import com.noteboard.noteboard.dto.CommentDTO;
import com.noteboard.noteboard.entity.Comment;
import com.noteboard.noteboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;


    @GetMapping("/api/v1/{postId}/comments")
    public List<CommentDTO> findComments(@PathVariable Long postId){
        List<Comment> comments = commentService.findCommentsByPostId(postId);
        List<CommentDTO> result = comments.stream().map(comment -> new CommentDTO(comment)).collect(Collectors.toList());
        return result;
    }
}
