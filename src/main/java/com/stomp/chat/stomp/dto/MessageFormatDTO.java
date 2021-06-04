package com.stomp.chat.stomp.dto;

import lombok.Data;

@Data
public class MessageFormatDTO {
    
    private Long chatRoomid;
    private String user;
    private String message;

}
