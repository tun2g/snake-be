package com.example.tram.modules.game;

import com.example.tram.config.response.Response;
import com.example.tram.modules.game.interface_game.IGameController;
import com.example.tram.modules.game.interface_game.IGameService;
import com.example.tram.modules.game.request.CheckRoomRequest;
import com.example.tram.modules.game.response.CheckRoomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class GameControllerImpl implements IGameController {

    @Autowired
    private IGameService gameService;

    @Override
    public ResponseEntity<?> checkRoom(CheckRoomRequest checkRoomRequest){
        CheckRoomResponse checkRoomResponse = gameService.checkRoom(checkRoomRequest);
        return Response.SuccessResponse(checkRoomResponse,"success");
    }
}
