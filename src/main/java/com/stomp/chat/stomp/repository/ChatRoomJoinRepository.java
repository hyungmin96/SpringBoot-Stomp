package com.stomp.chat.stomp.repository;

import java.util.List;

import com.stomp.chat.stomp.controller.ChatRoomJoin;
import com.stomp.chat.stomp.model.MemberVo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomJoinRepository extends JpaRepository<ChatRoomJoin, Long>{

    public List<ChatRoomJoin> findAllBytarget(MemberVo target);
    public List<ChatRoomJoin> findAllBymember(MemberVo user);


}
