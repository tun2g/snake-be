package com.example.tram.websocket.chat_message;

import com.example.tram.websocket.chat_message.interface_chat.IChatController;
import com.example.tram.websocket.chat_message.interface_chat.IChatService;
import com.example.tram.websocket.chat_message.payload.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.stereotype.Controller;

@Controller
public class ChatControllerImpl implements IChatController {

    @Autowired
    private IChatService chatService;

    @Override
    public void sendMessage(@DestinationVariable String room, ChatMessage chatMessage) {
        chatService.sendMessage(room,chatMessage);
    }
}
