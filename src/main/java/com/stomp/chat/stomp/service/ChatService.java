package com.stomp.chat.stomp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stomp.chat.stomp.model.ChatRoomVo;
import com.stomp.chat.stomp.model.ChatVo;
import com.stomp.chat.stomp.repository.ChatRepository;
import com.stomp.chat.stomp.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired 
    private ChatRoomRepository chatRoomRepository;

    public void saveChatContent(ChatVo chatVo){
        chatRepository.save(chatVo);
    }

    public Map<LocalDate, List<ChatVo>> getChatContent(int page, int display, long roomId){
        
        PageRequest request = PageRequest.of(page, display, Sort.Direction.DESC, "id");
        ChatRoomVo chatRoomVo = chatRoomRepository.findById(roomId);
        
        Page<ChatVo> chattings = chatRepository.findAllBychatRoomVo(chatRoomVo, request);

        Map<LocalDate, List<ChatVo>> dateSet = new HashMap<>();

        chattings.forEach(action -> {
            dateSet.put(action.getTime().toLocalDate(), new ArrayList<ChatVo>());
        });

        dateSet.forEach((key, value) -> {
            chattings.forEach(action -> {
                if(key.equals(action.getTime().toLocalDate())){
                    value.add(action);
                }
            });
        });
        
        return dateSet;

    }

}
