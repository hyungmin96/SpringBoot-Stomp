package com.stomp.chat.stomp.model;

import lombok.Data;

@Data
public class MessageFormat {
    
    private Long chatRoomid;
    private String user;
    private String target;
    private String message;

}
