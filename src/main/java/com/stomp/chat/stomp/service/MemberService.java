package com.stomp.chat.stomp.service;

import com.stomp.chat.stomp.model.MemberVo;
import com.stomp.chat.stomp.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    
    @Autowired
    private MemberRepository memberRepository;

    public MemberVo findUserObject(String username){
        return memberRepository.findByusername(username);
    }

}
