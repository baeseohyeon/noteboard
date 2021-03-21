package com.noteboard.noteboard.repository;

import com.noteboard.noteboard.entity.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UploadFileRepository extends JpaRepository<UploadFile,Long> {

    @Query("select f from UploadFile f where f.post.id = :id")
    List<UploadFile> findAllByPostId(Long id);
}
