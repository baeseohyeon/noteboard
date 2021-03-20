package com.noteboard.noteboard.repository;

import com.noteboard.noteboard.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {


}
