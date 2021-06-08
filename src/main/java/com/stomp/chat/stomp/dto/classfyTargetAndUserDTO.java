package com.stomp.chat.stomp.dto;

import com.stomp.chat.stomp.controller.ChatRoomJoin;
import lombok.Data;

@Data
public class classfyTargetAndUserDTO {
    
    private Long roomId;
    private String target;
    private ChatRoomJoin chatRoomJoin;

}
