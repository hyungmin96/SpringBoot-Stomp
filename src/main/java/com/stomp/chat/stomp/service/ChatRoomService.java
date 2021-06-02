package com.stomp.chat.stomp.service;

import java.util.List;

import com.stomp.chat.stomp.model.ChatRoomVo;
import com.stomp.chat.stomp.repository.ChatRoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomService {
    
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public void saveObject(ChatRoomVo chatRoomVo){
        chatRoomRepository.save(chatRoomVo);
    }

}
