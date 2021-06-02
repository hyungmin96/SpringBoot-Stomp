package com.stomp.chat.stomp.repository;

import java.util.List;

import com.stomp.chat.stomp.controller.ChatRoomJoin;
import com.stomp.chat.stomp.model.MemberVo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ChatRoomJoinRepository extends JpaRepository<ChatRoomJoin, Long>{

    public ChatRoomJoin findBymemberAndTarget(MemberVo member, String target);
}
