package com.stomp.chat.stomp.controller;

import java.util.List;

import com.stomp.chat.stomp.model.ChatVo;
import com.stomp.chat.stomp.service.ChatRoomJoinService;
import com.stomp.chat.stomp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String home(Model model){

        List<ChatRoomJoin> roomList = chatRoomJoinService.getRoomList();

        model.addAttribute("roomList", roomList);
        return "/createRoom/createRoom";
    }

    @GetMapping("/api/chat/{roomid}")
    public String connectRoom(Model model, @PathVariable long roomid){
        
        List<ChatVo> chats = chatService.getChatContent(roomid);

        model.addAttribute("roomid", roomid);
        model.addAttribute("chats", chats);

        return "/main/index";
    }

    @GetMapping("/user/login")
    public String login(){

        return "/login/login";
    }

}
