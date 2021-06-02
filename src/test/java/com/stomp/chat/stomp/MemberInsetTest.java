package com.stomp.chat.stomp;

import com.stomp.chat.stomp.model.MemberVo;
import com.stomp.chat.stomp.service.MemberService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application.yml")
public class MemberInsetTest {
    
    @Autowired
    private MemberService memberSerivce;

    @Test
    void 멤버_리포지토리_저장_테스트(){

        MemberVo memberVo = MemberVo.builder()
                            .username("123")
                            .password("123")
                            .name("123").build();

        memberSerivce.saveObject(memberVo);

        memberVo = MemberVo.builder()
                            .username("1234")
                            .password("1234")
                            .name("1234").build();

        memberSerivce.saveObject(memberVo);

    }

}