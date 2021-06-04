package com.stomp.chat.stomp.dto;

import lombok.Data;

@Data
public class notificationDTO {

    private boolean createResult;
    private String notificationType;
    private Long roomId;
    private String user;
    private String target;
    private String message;

}
