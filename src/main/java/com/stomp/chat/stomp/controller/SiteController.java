package com.stomp.chat.stomp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class SiteController {
    
    @GetMapping("/")
    public String home(){
        return "createRoom";
    }

    @GetMapping("/api/target={target}/chat/{roomid}")
    public String connectRoom(Model model, @PathVariable String target, @PathVariable long roomid){
        
        model.addAttribute("target", target);
        model.addAttribute("roomid", roomid);

        return "index";
    }

    @GetMapping("/user/login")
    public String login(){

        return "login";
    }

}
