package com.stomp.chat.stomp.controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.stomp.chat.stomp.model.ChatRoomVo;
import com.stomp.chat.stomp.model.MemberVo;


import lombok.Data;

@Data
@Entity
public class ChatRoomJoin {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private MemberVo member;

    @Column
    private String target;

    @ManyToOne
    @JoinColumn(name = "roomId")
    private ChatRoomVo chatRoomVo;

}
