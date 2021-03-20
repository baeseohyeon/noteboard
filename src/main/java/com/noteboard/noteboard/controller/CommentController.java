package com.noteboard.noteboard.controller;

import com.noteboard.noteboard.entity.Account;
import com.noteboard.noteboard.entity.Comment;
import com.noteboard.noteboard.entity.Post;
import com.noteboard.noteboard.service.AccountService;
import com.noteboard.noteboard.service.CommentService;
import com.noteboard.noteboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {


    private final AccountService accountService;
    private final PostService postService;
    private final CommentService commentService;





    @PostMapping("/post/{postId}/comment")
    public String createComment(@PathVariable Long postId, Comment comment, Principal principal){
        String username = principal.getName();
        Account account = accountService.findByName(username);
        Post post=postService.findOne(postId);
        commentService.addComment(comment,post,account);
        commentService.saveComment(comment);
        return "redirect:/post/{postId}";
    }

    @PostMapping("/post/{postId}/comment/{commentId}")
    public String updateComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody Comment comment){
        commentService.updateComment(comment.getId(),comment.getWriter(),comment.getContent());
        return "/post/{postId}";

    }
    @DeleteMapping("/post/{postId}/comment/{commentId}")
    public String deleteComment(@PathVariable Long postId, @PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return "Comment Delete Success!";
    }

}
