package com.stomp.chat.stomp.repository;

import com.stomp.chat.stomp.model.MemberVo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberVo, Long>{
    public MemberVo findByusername(String username);
    public MemberVo findByUsername(String username);
}
