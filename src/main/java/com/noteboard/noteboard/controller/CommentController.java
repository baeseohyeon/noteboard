package com.noteboard.noteboard.controller;

import com.noteboard.noteboard.entity.Comment;
import com.noteboard.noteboard.entity.Post;
import com.noteboard.noteboard.service.CommentService;
import com.noteboard.noteboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {


    private final PostService postService;
    private final CommentService commentService;



    @GetMapping("/post/{postId}/comment")
    public List<Comment> getPostComments(@PathVariable Long postId){
        Post post = postService.findOne(postId);
        return commentService.findCommentsInPost(post);
    }


    @PutMapping("/post/{postId}/comment")
    public Comment createComment(@PathVariable Long postId, @RequestBody Comment comment){
        Post post=postService.findOne(postId);
        comment.setPost(post);
        commentService.saveComment(comment);
        return comment;
    }

    @PostMapping("/post/{postId}/comment/{commentId}")
    public String updateComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody Comment comment){
        commentService.updateComment(comment.getId(),comment.getTitle(),comment.getWriter(),comment.getContent());
        return "/post/{postId}";

    }
    @DeleteMapping("/post/{postId}/comment/{commentId}")
    public String deleteComment(@PathVariable Long postId, @PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return "Comment Delete Success!";
    }

}
