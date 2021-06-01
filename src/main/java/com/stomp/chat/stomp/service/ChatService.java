package com.stomp.chat.stomp.service;

import com.stomp.chat.stomp.model.ChatVo;
import com.stomp.chat.stomp.repository.ChatRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public void saveChatContent(ChatVo chatVo){
        chatRepository.save(chatVo);
    }

    public Page<ChatVo> getChatContent(int roomId){
        
        PageRequest request = PageRequest.of(0, Integer.MAX_VALUE);
        Page<ChatVo> chattings = chatRepository.findAllById(roomId, request);

        return chattings;
    }

}
