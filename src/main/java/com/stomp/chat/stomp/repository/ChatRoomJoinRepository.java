package com.stomp.chat.stomp.repository;

import java.util.List;

import com.stomp.chat.stomp.controller.ChatRoomJoin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomJoinRepository extends JpaRepository<ChatRoomJoin, Long>{
    
}
