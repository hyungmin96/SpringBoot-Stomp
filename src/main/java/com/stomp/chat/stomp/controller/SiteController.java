package com.stomp.chat.stomp.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SiteController {
    
    @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetails principal){
        return "/createRoom/chatRoomList";
    }

    @GetMapping("/api/chat/target={target}/room={roomid}")
    public String connectRoom(Model model, @PathVariable long roomid, @PathVariable String target){

        model.addAttribute("roomid", roomid);
        model.addAttribute("target", target);

        return "/main/chatroom";
    }

    @GetMapping("/user/login")
    public String login(){
        return "/login/login";
    }

}
