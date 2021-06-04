package com.stomp.chat.stomp.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    
    public List<ChatRoomJoin> getRooms(MemberVo user){
        
        // user가 target으로 되어있는 리스트 추출
        List<ChatRoomJoin> targetRooms = chatRoomJoinRepository.findAllBytarget(user);

        // user가 target으로 되어있는 리스트 추출
        List<ChatRoomJoin> userRooms = chatRoomJoinRepository.findAllBymember(user);
        
        List<ChatRoomJoin> Rooms = Stream.concat(targetRooms.stream(),
                                    userRooms.stream()).collect(Collectors.toList());

        return Rooms;
    }

    public void saveObject(ChatRoomJoin chatRoomJoin){
        chatRoomJoinRepository.save(chatRoomJoin);
    }

    public List<ChatRoomJoin> getRoomList(){
        return chatRoomJoinRepository.findAll();
    }

}
