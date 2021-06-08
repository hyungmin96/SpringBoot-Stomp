package com.stomp.chat.stomp.service;

import java.util.List;
import com.stomp.chat.stomp.controller.ChatRoomJoin;
import com.stomp.chat.stomp.model.MemberVo;
import com.stomp.chat.stomp.repository.ChatRoomJoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomJoinService{
    
    @Autowired
    private ChatRoomJoinRepository chatRoomJoinRepository;
    
    public List<ChatRoomJoin> getRooms(MemberVo user){
        return chatRoomJoinRepository.findAllBymemberOrTarget(user, user);
    }

    public void saveObject(ChatRoomJoin chatRoomJoin){
        chatRoomJoinRepository.save(chatRoomJoin);
    }

}
