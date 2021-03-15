package com.noteboard.noteboard.repository;

import com.noteboard.noteboard.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByTitleContaining(String keyword);
}
