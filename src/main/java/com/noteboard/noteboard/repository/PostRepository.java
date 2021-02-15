package com.noteboard.noteboard.repository;

import com.noteboard.noteboard.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
