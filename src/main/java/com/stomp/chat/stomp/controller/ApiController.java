package com.stomp.chat.stomp.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.stomp.chat.stomp.components.CheckChatRoomDuplicated;
import com.stomp.chat.stomp.dto.classfyTargetAndUserDTO;
import com.stomp.chat.stomp.dto.notificationDTO;
import com.stomp.chat.stomp.model.ChatVo;
import com.stomp.chat.stomp.model.MemberVo;
import com.stomp.chat.stomp.service.ChatRoomJoinService;
import com.stomp.chat.stomp.service.ChatRoomService;
import com.stomp.chat.stomp.service.ChatService;
import com.stomp.chat.stomp.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    
    @Autowired
    private MemberService memberService;

    @Autowired
    private ChatMessageController chatMessageController;

    @Autowired
    private ChatRoomJoinService chatRoomJoinService;

    @Autowired
    private ChatRoomService chatRoomService;
    
    @Autowired
    private ChatService chatService;
    
    @GetMapping("/chat/rooms")
    public List<classfyTargetAndUserDTO> getChatRooms(@AuthenticationPrincipal UserDetails principal){

        MemberVo user = (MemberVo) principal;

        List<ChatRoomJoin> roomList = chatRoomJoinService.getRooms(user);
        List<classfyTargetAndUserDTO> chatRoomList = new ArrayList<>();

        roomList.forEach(action -> {
            classfyTargetAndUserDTO classfy = new classfyTargetAndUserDTO();
            classfy.setRoomId(action.getChatRoomVo().getId());
            classfy.setChatRoomJoin(action);

            if(action.getTarget().equals(user))
                classfy.setTarget(action.getMember().getUsername());
            else
                classfy.setTarget(action.getTarget().getUsername());
            
            chatRoomList.add(classfy);
        });
        
        return chatRoomList;
    }

    @GetMapping("/chat/chats/")
    public Page<ChatVo> getChatList(@RequestParam Long roomId, @RequestParam int display, @RequestParam int page){
        Page<ChatVo> chatList = chatService.getChatContent(page, display, roomId);
        return chatList;
    }

    @PostMapping("/create/chatroom")
    public notificationDTO createRoom(@RequestParam("user") String user, 
                                @RequestParam("target") String target){

        CheckChatRoomDuplicated checkChatRoomDuplicated = 
                    new CheckChatRoomDuplicated(memberService, chatRoomJoinService,
                                                chatRoomService, user, target);
                    
        Long roomNo =  checkChatRoomDuplicated.checkRoom();

        notificationDTO createRoomResponse = new notificationDTO();
        
        if (roomNo != 0L) {
            createRoomResponse.setCreateResult(true);
            createRoomResponse.setMessage("1:1 대화알림이 도착하였습니다.");
            createRoomResponse.setNotificationType("chat");
            createRoomResponse.setRoomId(roomNo);
            createRoomResponse.setTarget(target);
            createRoomResponse.setUser(user);

            chatMessageController.notification(target, createRoomResponse);
        }else{
            createRoomResponse.setCreateResult(false);
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
