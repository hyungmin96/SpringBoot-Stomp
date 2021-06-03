package com.stomp.chat.stomp.repository;


import com.stomp.chat.stomp.model.ChatRoomVo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoomVo, Long>{
    public ChatRoomVo findById(long roomId);
}
