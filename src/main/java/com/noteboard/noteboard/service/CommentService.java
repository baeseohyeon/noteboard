package com.noteboard.noteboard.service;


import com.noteboard.noteboard.entity.Comment;
import com.noteboard.noteboard.entity.Post;
import com.noteboard.noteboard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;


    public List<Comment> findCommentsInPost(Post post) {
        return commentRepository.findCommentsByPost(post);
    }

    public Long saveComment(Comment comment){
        commentRepository.save(comment);
        return comment.getId();
    }

    public void deleteComment(Long commentId){
        commentRepository.deleteById(commentId);
    }

    public void updateComment(Long id, String title, String writer,String content){
        Comment comment=commentRepository.findById(id).get();
        comment.setContent(content);
        comment.setWriter(writer);
        comment.setTitle(title);
    }
}
