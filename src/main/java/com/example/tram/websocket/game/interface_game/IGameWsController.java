package com.example.tram.websocket.game.interface_game;

import com.example.tram.websocket.game.payload.WaitingRoomPayload;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

public interface IGameWsController {

    @MessageMapping("/room/waiting")
    void waitForGame(SimpMessageHeaderAccessor headerAccessor, @Payload WaitingRoomPayload waitingRoomPayload);

    @MessageMapping("/room/leave")
    void leaveWaitingRoom(SimpMessageHeaderAccessor headerAccessor, @Payload WaitingRoomPayload waitingRoomPayload);

    @MessageMapping("/game/start/{room}")
    void startGame(@DestinationVariable String room);
}
