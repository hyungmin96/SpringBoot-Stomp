package com.stomp.chat.stomp.controller;

import com.stomp.chat.stomp.model.Greeting;
import com.stomp.chat.stomp.model.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SiteController {
    
    @GetMapping("/")
    public String home(){
        return "index";
    }

    @MessageMapping("/hello")
    @SendTo("/sub/greetings")
    public Greeting greeting(HelloMessage message){
        return new Greeting("hello user : " + message.getName() + "!");
    }

}
