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

        for(int i = 0; i < 30; i ++){
            MemberVo memberVo = MemberVo.builder()
                                .username(String.valueOf(i))
                                .password(new BCryptPasswordEncoder().encode(String.valueOf(i)))
                                .name(String.valueOf(i)).build();

            memberSerivce.saveObject(memberVo);
        }
    }
}
