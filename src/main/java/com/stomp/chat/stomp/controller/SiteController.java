package com.stomp.chat.stomp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SiteController {
    
    @GetMapping("/")
    public String home(){
        return "createRoom";
    }

    @GetMapping("/room/{roomId}")
    public String connectRoom(){
        return "index";
    }

}
