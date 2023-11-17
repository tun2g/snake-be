package com.example.tram.modules.game.interface_game;

import com.example.tram.modules.game.request.CheckRoomRequest;
import com.example.tram.modules.game.response.CheckRoomResponse;
import org.springframework.stereotype.Service;

@Service
public interface IGameService {
    CheckRoomResponse checkRoom(CheckRoomRequest checkRoomRequest);
}
