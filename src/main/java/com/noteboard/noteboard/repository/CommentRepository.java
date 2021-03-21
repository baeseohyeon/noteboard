package com.noteboard.noteboard.repository;

import com.noteboard.noteboard.entity.Comment;
import com.noteboard.noteboard.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findCommentsByPost(Post post);

    @Query("select c from Comment c where c.post.id = :postId")
    List<Comment> findCommentsByPostId(@Param("postId") Long postId);

}
