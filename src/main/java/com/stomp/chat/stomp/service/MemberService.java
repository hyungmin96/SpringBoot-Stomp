package com.stomp.chat.stomp.service;


import com.stomp.chat.stomp.model.MemberVo;
import com.stomp.chat.stomp.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements UserDetailsService{
    
    @Autowired
    private MemberRepository memberRepository;

    public MemberVo findUserObject(String username){

        MemberVo memberVo = memberRepository.findByusername(username);

        if(memberVo == null)
            memberVo = new MemberVo();

        return memberVo;
    }

    public void saveObject(MemberVo memberVo){
        memberRepository.save(memberVo);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberVo user = memberRepository.findByUsername(username);
        if(user != null)
            return user;
        else
            return new MemberVo();
    }

}
