package com.stomp.chat.stomp.model;

import lombok.Data;

@Data
public class CreateRoomResponse{
    
    private String user;
    private Long roomId;
    private String target;

}
