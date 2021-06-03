package com.stomp.chat.stomp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stomp.chat.stomp.controller.ChatRoomJoin;
import com.stomp.chat.stomp.model.MemberVo;
import com.stomp.chat.stomp.repository.ChatRoomJoinRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomJoinService{
    
    @Autowired
    private ChatRoomJoinRepository chatRoomJoinRepository;
    
    ChatRoomJoin room = null;
    
    public Map<String, List<ChatRoomJoin>> getRooms(MemberVo user, MemberVo target){
        
        Map<String, List<ChatRoomJoin>> hashmap = new HashMap<String, List<ChatRoomJoin>>();
        
        hashmap.put("target", chatRoomJoinRepository.findAllBytarget(target));
        hashmap.put("user", chatRoomJoinRepository.findAllBymember(user));
        
        return hashmap;
    }

    public void saveObject(ChatRoomJoin chatRoomJoin){
        chatRoomJoinRepository.save(chatRoomJoin);
    }

    public List<ChatRoomJoin> getRoomList(){
        return chatRoomJoinRepository.findAll();
    }

}
