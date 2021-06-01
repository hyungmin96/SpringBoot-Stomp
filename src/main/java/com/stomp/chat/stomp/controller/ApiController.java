package com.stomp.chat.stomp.controller;

import com.stomp.chat.stomp.components.CheckChatRoomDuplicated;
import com.stomp.chat.stomp.model.ChatRoomVo;
import com.stomp.chat.stomp.service.ChatRoomService;
import com.stomp.chat.stomp.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    
    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private MemberService memberService;

    // target = 채팅초대할 대상
    // user = 채팅 아이디(로그인 계정)
    @PostMapping("/create/chatroom")
    public String createRoom(@RequestParam("user") String user, 
                                @RequestParam("target") String target){

        CheckChatRoomDuplicated checkChatRoomDuplicated = 
                    new CheckChatRoomDuplicated(memberService, user, target);
                    
        checkChatRoomDuplicated.checkRoom();

        return "redirect:/room/{id}";

    }

}
