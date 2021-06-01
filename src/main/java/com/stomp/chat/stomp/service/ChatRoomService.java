package com.stomp.chat.stomp.service;

import com.stomp.chat.stomp.model.ChatRoomVo;
import com.stomp.chat.stomp.repository.ChatRoomJoinRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomService {
    
    @Autowired
    private ChatRoomJoinRepository chatRoomJoinRepository;


}
