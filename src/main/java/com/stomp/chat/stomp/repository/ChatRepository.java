package com.stomp.chat.stomp.repository;

import java.util.List;

import com.stomp.chat.stomp.model.ChatRoomVo;
import com.stomp.chat.stomp.model.ChatVo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<ChatVo, Long> {
    public List<ChatVo> findAllBychatRoomVo(ChatRoomVo chatRoomVo);
}
