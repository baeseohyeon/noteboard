package com.noteboard.noteboard.repository;

import com.noteboard.noteboard.entity.Comment;
import com.noteboard.noteboard.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findCommentsByPost(Post post);
}
