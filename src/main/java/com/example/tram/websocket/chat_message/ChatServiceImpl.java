package com.example.tram.websocket.chat_message;

import com.example.tram.websocket.chat_message.interface_chat.IChatService;
import com.example.tram.websocket.chat_message.payload.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChatServiceImpl implements IChatService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);

    @Override
    public void sendMessage(String room, ChatMessage chatMessage){
        logger.info("send message to " + room);
        simpMessagingTemplate.convertAndSend("/ws/chat/" + room,chatMessage);
    }
}
