package com.stomp.chat.stomp.repository;
import com.stomp.chat.stomp.model.ChatVo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<ChatVo, Long> {
    public Page<ChatVo> findAllById(Integer Id, PageRequest request);
}
