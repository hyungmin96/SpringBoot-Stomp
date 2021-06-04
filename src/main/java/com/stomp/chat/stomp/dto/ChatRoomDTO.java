package com.stomp.chat.stomp.dto;

import lombok.Data;

@Data
public class ChatRoomDTO {
    
    private String user;
    private String target;
    private long chatRoomId;

}
