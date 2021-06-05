package com.stomp.chat.stomp.controller;

import java.util.LinkedList;
import java.util.List;
import com.stomp.chat.stomp.model.ChatVo;
import com.stomp.chat.stomp.model.MemberVo;
import com.stomp.chat.stomp.model.classfyTargetAndUser;
import com.stomp.chat.stomp.service.ChatRoomJoinService;
import com.stomp.chat.stomp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SiteController {
    
    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRoomJoinService chatRoomJoinService;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetails principal, Model model){

        MemberVo user = (MemberVo) principal;

        List<ChatRoomJoin> roomList = chatRoomJoinService.getRoomList(user);
        List<classfyTargetAndUser> targetList = new LinkedList<>();

        roomList.forEach(action -> {
            classfyTargetAndUser classfy = new classfyTargetAndUser();
            classfy.setRoomId(action.getChatRoomVo().getId());

            if(action.getTarget().equals(user))
                classfy.setTarget(action.getMember().getUsername());
            else
                classfy.setTarget(action.getTarget().getUsername());
            
            targetList.add(classfy);
        });

        model.addAttribute("roomList", targetList);
        return "/createRoom/createRoom";
    }

    @GetMapping("/api/chat/target={target}/room={roomid}")
    public String connectRoom(Model model, @PathVariable long roomid, @PathVariable String target){
        
        List<ChatVo> chats = chatService.getChatContent(roomid);

        model.addAttribute("roomid", roomid);
        model.addAttribute("target", target);
        model.addAttribute("chats", chats);

        return "/main/chatroom";
    }

    @GetMapping("/user/login")
    public String login(){
        return "/login/login";
    }

}
