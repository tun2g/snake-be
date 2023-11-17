package com.example.tram.websocket.game;

import com.example.tram.websocket.game.interface_game.IGameWsController;
import com.example.tram.websocket.game.interface_game.IGameWsService;
import com.example.tram.websocket.game.payload.WaitingRoomPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class GameWsControllerImpl implements IGameWsController {

    @Autowired
    private IGameWsService gameService;

    @Override
    public void waitForGame(SimpMessageHeaderAccessor headerAccessor, WaitingRoomPayload waitingRoomPayload){
        String sessionId = headerAccessor.getSessionAttributes().get("sessionId").toString();
        gameService.waitForGame(sessionId,waitingRoomPayload);
    }

    @Override
    public void leaveWaitingRoom(SimpMessageHeaderAccessor headerAccessor,WaitingRoomPayload waitingRoomPayload){
        String sessionId = headerAccessor.getSessionAttributes().get("sessionId").toString();
        gameService.leaveWaitingGame(sessionId,waitingRoomPayload);
    }

    @Override
    public void startGame(@DestinationVariable String room){
        gameService.startGame(room);
    }
}
