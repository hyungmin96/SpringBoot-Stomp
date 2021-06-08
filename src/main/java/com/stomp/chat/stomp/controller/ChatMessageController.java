package com.stomp.chat.stomp.controller;

import java.security.Principal;

import com.stomp.chat.stomp.dto.MessageFormatDTO;
import com.stomp.chat.stomp.dto.notificationDTO;
import com.stomp.chat.stomp.model.ChatVo;
import com.stomp.chat.stomp.repository.ChatRoomRepository;
import com.stomp.chat.stomp.repository.MemberRepository;
import com.stomp.chat.stomp.service.ChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatMessageController {
        
    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    
    @MessageMapping("/send/chat")
    public MessageFormatDTO content(Principal principal, @Payload MessageFormatDTO message){

        message.setUser(principal.getName());

        ChatVo chatVo = ChatVo.builder()
                                .content(message.getMessage())
                                .chatRoomVo(chatRoomRepository.getById(message.getChatRoomid()))
                                .member(memberRepository.findByusername(message.getUser()))
                                .build();

        chatService.saveChatContent(chatVo);
        simpMessagingTemplate.convertAndSend("/queue/websocket/" + message.getChatRoomid(), message);
        return message;

    }

    public void notification(String target, notificationDTO notification){
        simpMessagingTemplate.convertAndSend("/queue/notification/" + target, notification);
    }

}
