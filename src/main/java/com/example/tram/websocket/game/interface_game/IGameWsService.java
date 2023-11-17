package com.example.tram.websocket.game.interface_game;

import com.example.tram.websocket.game.payload.WaitingRoomPayload;
import org.springframework.stereotype.Service;

@Service
public interface IGameWsService {

    void waitForGame(String sessionId, WaitingRoomPayload waitingRoomPayload);

    void leaveWaitingGame(String sessionId, WaitingRoomPayload waitingRoomPayload);

    void startGame(String room);
}
