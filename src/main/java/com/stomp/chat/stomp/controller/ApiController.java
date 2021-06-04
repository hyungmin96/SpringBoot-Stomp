package com.stomp.chat.stomp.controller;

import com.stomp.chat.stomp.components.CheckChatRoomDuplicated;
import com.stomp.chat.stomp.model.CreateRoomResponse;
import com.stomp.chat.stomp.model.MemberVo;
import com.stomp.chat.stomp.service.ChatRoomJoinService;
import com.stomp.chat.stomp.service.ChatRoomService;
import com.stomp.chat.stomp.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    
    @Autowired
    private ChatRoomJoinService chatRoomJoinService;

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private MemberService memberService;

    // target = 채팅초대할 대상
    // user = 채팅 아이디(로그인 계정)
    @PostMapping("/create/chatroom")
    public CreateRoomResponse createRoom(@RequestParam("user") String user, 
                                @RequestParam("target") String target){

        CheckChatRoomDuplicated checkChatRoomDuplicated = 
                    new CheckChatRoomDuplicated(memberService, chatRoomJoinService, 
                                                chatRoomService, user, target);
                    
        Long roomNo =  checkChatRoomDuplicated.checkRoom();

        CreateRoomResponse createRoomResponse = new CreateRoomResponse();
        
        if (roomNo != 0L) {
            createRoomResponse.setUser(user);
            createRoomResponse.setTarget(target);
            createRoomResponse.setRoomId(roomNo);
        }

        return createRoomResponse;
    }

    @PostMapping("/login")
    public String login(@RequestBody MemberVo memberVo){
        UserDetails account = memberService.loadUserByUsername(memberVo.getUsername());
        if(account != null)
            return "로그인 성공";
        else
            return "로그인 실패";
    }

}
