package com.stomp.chat.stomp.controller;

import com.stomp.chat.stomp.model.ChatVo;
import com.stomp.chat.stomp.model.MessageFormat;
import com.stomp.chat.stomp.repository.ChatRoomRepository;
import com.stomp.chat.stomp.repository.MemberRepository;
import com.stomp.chat.stomp.service.ChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatMessageController {
        
    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private MemberRepository memberRepository;

    @MessageMapping("/greeting")
    @SendTo("/sub/greetings")
    public MessageFormat enter(MessageFormat message){
        
        return message;
    }

    @MessageMapping("/content")
    @SendTo("/sub/greetings")
    public MessageFormat content(MessageFormat message){
    
        ChatVo chatVo = ChatVo.builder()
                                .content(message.getMessage())
                                .chatRoomVo(chatRoomRepository.getById(message.getChatRoomid()))
                                .member(memberRepository.findByusername(message.getUser()))
                                .build();

        chatService.saveChatContent(chatVo);

        return message;

    }

}
