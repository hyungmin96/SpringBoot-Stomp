package com.stomp.chat.stomp.components;

import java.util.List;

import com.stomp.chat.stomp.controller.ChatMessageController;
import com.stomp.chat.stomp.controller.ChatRoomJoin;
import com.stomp.chat.stomp.model.ChatRoomVo;
import com.stomp.chat.stomp.model.MemberVo;
import com.stomp.chat.stomp.service.ChatRoomJoinService;
import com.stomp.chat.stomp.service.ChatRoomService;
import com.stomp.chat.stomp.service.MemberService;

public class CheckChatRoomDuplicated {
    
    ChatRoomJoinService chatRoomJoinService;
    ChatRoomService chatRoomService;

    MemberVo targetVo = null;
    MemberVo userVo = null;
    ChatRoomJoin room = null;

    public CheckChatRoomDuplicated(MemberService memberService,
                                    ChatRoomJoinService chatRoomJoinService,
                                    ChatRoomService chatRoomService,
                                    String userString, String targetString){

        this.chatRoomJoinService = chatRoomJoinService;
        this.chatRoomService = chatRoomService;

        this.userVo = memberService.findUserObject(userString);
        this.targetVo = memberService.findUserObject(targetString);

    }

    public Long checkRoom(){

        if (targetVo.getId() != null) {

            List<ChatRoomJoin> rooms = chatRoomJoinService.getRooms(userVo);

            for (ChatRoomJoin item : rooms) {
                if (item.getTarget().equals(targetVo) || item.getMember().equals(targetVo)) {
                    room = item;
                    break;
                }
            }

            if (room == null) {

                ChatRoomVo chatRoomVo = new ChatRoomVo();
                chatRoomService.saveObject(chatRoomVo);

                room = new ChatRoomJoin();
                room.setChatRoomVo(chatRoomVo);
                room.setMember(userVo);
                room.setTarget(targetVo);
                chatRoomJoinService.saveObject(room);

            }

        }

        if(room != null)
            return room.getChatRoomVo().getId();
        else
            return 0L;

    }

}
