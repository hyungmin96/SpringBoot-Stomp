package com.stomp.chat.stomp;

import java.util.List;

import com.stomp.chat.stomp.controller.ChatRoomJoin;
import com.stomp.chat.stomp.model.ChatRoomVo;
import com.stomp.chat.stomp.model.MemberVo;
import com.stomp.chat.stomp.service.ChatRoomJoinService;
import com.stomp.chat.stomp.service.ChatRoomService;
import com.stomp.chat.stomp.service.MemberService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application.yml")
public class ChatRoomInserTest {
    
    @Autowired
    private MemberService memberService;

    @Autowired
    private ChatRoomJoinService chatRoomJoinService;

    @Autowired
    private ChatRoomService chatRoomService;

    @Test
    void 채팅방_개설_및_DB저장(){

        String targetString = "123";
        String userString = "1234";

        MemberVo targetVo = memberService.findUserObject(targetString);
        MemberVo userVo = memberService.findUserObject(userString);

        // List<ChatRoomJoin> targetRooms = chatRoomJoinService.getRooms(targetVo);
        ChatRoomVo chatRoomVo = new ChatRoomVo();
        chatRoomService.saveObject(chatRoomVo);

        ChatRoomJoin chatRoomJoin = new ChatRoomJoin();
        chatRoomJoin.setChatRoomVo(chatRoomVo);
        chatRoomJoin.setMember(userVo);
        chatRoomJoin.setTarget(targetVo.getUsername());
        chatRoomJoinService.saveObject(chatRoomJoin);

    }

    @Test
    void 개설채팅방_연결(){

        String targetString = "123";
        String userString = "1234";

        MemberVo userVo = memberService.findUserObject(userString);

        ChatRoomJoin result = chatRoomJoinService.getRooms(userVo, targetString);

        if(result != null)
            System.out.println(result.getTarget() + "/" + result.getId() + "번 채팅방이 생성됨");
        else
            System.out.println("채팅방 개설 실패");
    }

    @Test
    void 개설채팅방_채팅내용_저장(){

    }

}
