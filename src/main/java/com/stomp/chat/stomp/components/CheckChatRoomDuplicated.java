package com.stomp.chat.stomp.components;

import com.stomp.chat.stomp.model.MemberVo;
import com.stomp.chat.stomp.service.MemberService;

public class CheckChatRoomDuplicated {
    
    private String userString;
    private String targetString;
    private final MemberService memberService;

    public CheckChatRoomDuplicated(MemberService memberService, String userString, String targetString){
        this.memberService = memberService;
        this.userString = userString;
        this.targetString = targetString;
    }

    public void checkRoom(){

        MemberVo targetVo = memberService.findUserObject(this.targetString);
        MemberVo userVo = memberService.findUserObject(this.userString);

        return;
    }

}
