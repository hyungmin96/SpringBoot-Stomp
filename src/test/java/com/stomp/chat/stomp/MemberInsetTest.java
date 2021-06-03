package com.stomp.chat.stomp;

import com.stomp.chat.stomp.model.MemberVo;
import com.stomp.chat.stomp.service.MemberService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application.yml")
public class MemberInsetTest {
    
    @Autowired
    private MemberService memberSerivce;

    @Test
    void 멤버_회원가입_테스트(){

        MemberVo memberVo = MemberVo.builder()
                            .username("1")
                            .password(new BCryptPasswordEncoder().encode("1"))
                            .name("11").build();

        memberSerivce.saveObject(memberVo);

    }

}
