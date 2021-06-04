package com.stomp.chat.stomp.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stomp.chat.stomp.controller.ChatRoomJoin;
import com.stomp.chat.stomp.model.ChatRoomVo;
import com.stomp.chat.stomp.model.MemberVo;
import com.stomp.chat.stomp.service.ChatRoomJoinService;
import com.stomp.chat.stomp.service.ChatRoomService;
import com.stomp.chat.stomp.service.MemberService;

public class CheckChatRoomDuplicated {
    
    private final ChatRoomJoinService chatRoomJoinService;
    private final ChatRoomService chatRoomService;
    private final MemberService memberService;
    
    MemberVo targetVo = null;
    MemberVo userVo = null;
    ChatRoomJoin room = null;

    public CheckChatRoomDuplicated(MemberService memberService, 
                                    ChatRoomJoinService chatRoomJoinService,
                                    ChatRoomService chatRoomService,
                                    String userString, String targetString){

        this.memberService = memberService;
        this.chatRoomJoinService = chatRoomJoinService;
        this.chatRoomService = chatRoomService;

        this.userVo = memberService.findUserObject(userString);
        this.targetVo = memberService.findUserObject(targetString);

    }

    public Long checkRoom(){

        if (targetVo.getId() != null) {
            Map<String, List<ChatRoomJoin>> roomList = chatRoomJoinService.getRooms(userVo, targetVo);

            List<ChatRoomJoin> targetRooms = roomList.get("target");
            List<ChatRoomJoin> userRooms = roomList.get("user");

        if(room == null){

            ChatRoomVo chatRoomVo = new ChatRoomVo();
            chatRoomService.saveObject(chatRoomVo);
    
            ChatRoomJoin chatRoomJoin = new ChatRoomJoin();
            chatRoomJoin.setChatRoomVo(chatRoomVo);
            chatRoomJoin.setMember(userVo);
            chatRoomJoin.setTarget(targetVo);
            chatRoomJoinService.saveObject(chatRoomJoin);
    
            return chatRoomJoin.getChatRoomVo().getId();

        }

        return room.getChatRoomVo().getId();

        }else
            return 0L;

    }

}
