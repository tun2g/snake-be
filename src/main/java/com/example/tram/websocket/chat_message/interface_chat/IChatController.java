package com.example.tram.websocket.chat_message.interface_chat;


import com.example.tram.websocket.chat_message.payload.ChatMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;


public interface IChatController {

    @MessageMapping("/chat/send-message/{room}")
    void sendMessage(@DestinationVariable String room, @Payload ChatMessage chatMessage);

}
