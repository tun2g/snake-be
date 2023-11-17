package com.example.tram.websocket.chat_message.interface_chat;

import com.example.tram.websocket.chat_message.payload.ChatMessage;
import org.springframework.stereotype.Service;

@Service
public interface IChatService {
    void sendMessage(String room,ChatMessage chatMessage);
}
