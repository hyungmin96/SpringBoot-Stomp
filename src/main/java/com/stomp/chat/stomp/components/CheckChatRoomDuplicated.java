package com.stomp.chat.stomp.components;

import java.util.List;

import com.stomp.chat.stomp.controller.ChatRoomJoin;
import com.stomp.chat.stomp.model.ChatRoomVo;
import com.stomp.chat.stomp.model.MemberVo;
import com.stomp.chat.stomp.service.ChatRoomJoinService;
import com.stomp.chat.stomp.service.ChatRoomService;
import com.stomp.chat.stomp.service.MemberService;

public class CheckChatRoomDuplicated {
    
    private final MemberService memberService;
    private final ChatRoomJoinService chatRoomJoinService;
    private final ChatRoomService chatRoomService;
    
    MemberVo targetVo;
    MemberVo userVo;

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

        ChatRoomJoin result = chatRoomJoinService.getRooms(userVo, targetVo.getUsername());

        if(result != null)
            return result.getChatRoomVo().getId(); //채팅방 존재
        else
            return createRoom(); // 채팅방 없음

    }

    public Long createRoom(){

        ChatRoomVo chatRoomVo = new ChatRoomVo();
        chatRoomService.saveObject(chatRoomVo);

        ChatRoomJoin chatRoomJoin = new ChatRoomJoin();
        chatRoomJoin.setChatRoomVo(chatRoomVo);
        chatRoomJoin.setMember(userVo);
        chatRoomJoin.setTarget(targetVo.getUsername());
        chatRoomJoinService.saveObject(chatRoomJoin);

        return chatRoomJoin.getChatRoomVo().getId();

    }

}
