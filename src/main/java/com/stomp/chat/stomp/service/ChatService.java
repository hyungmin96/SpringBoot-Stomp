package com.stomp.chat.stomp.service;

import java.util.List;

import com.stomp.chat.stomp.model.ChatRoomVo;
import com.stomp.chat.stomp.model.ChatVo;
import com.stomp.chat.stomp.repository.ChatRepository;
import com.stomp.chat.stomp.repository.ChatRoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired 
    private ChatRoomRepository chatRoomRepository;

    public void saveChatContent(ChatVo chatVo){
        chatRepository.save(chatVo);
    }

    public Page<ChatVo> getChatContent(int page, int display, long roomId){
        
        PageRequest request = PageRequest.of(page, display, Sort.Direction.DESC, "id");
        ChatRoomVo chatRoomVo = chatRoomRepository.findById(roomId);
        
        Page<ChatVo> chattings = chatRepository.findAllBychatRoomVo(chatRoomVo, request);

        return chattings;

    }

}
