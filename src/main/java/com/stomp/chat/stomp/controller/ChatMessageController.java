package com.stomp.chat.stomp.controller;

import com.stomp.chat.stomp.model.ChatVo;
import com.stomp.chat.stomp.model.MessageFormat;
import com.stomp.chat.stomp.service.ChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatMessageController {
        
    @Autowired
    private ChatService chatService;

    @MessageMapping("/greeting")
    @SendTo("/sub/greetings")
    public MessageFormat enter(MessageFormat message){
        
        //chatService.saveChatContent(chatVo);
        return message;
    }

    @MessageMapping("/content")
    @SendTo("/sub/greetings")
    public MessageFormat content(MessageFormat message){
        //chatService.saveChatContent(chatVo);
        return message;
    }

}
