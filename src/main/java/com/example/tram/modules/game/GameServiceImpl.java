package com.example.tram.modules.game;

import com.example.tram.config.redis.IRedisCacheService;
import com.example.tram.modules.game.interface_game.IGameService;
import com.example.tram.modules.game.request.CheckRoomRequest;
import com.example.tram.modules.game.response.CheckRoomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GameServiceImpl implements IGameService {

    @Autowired
    private IRedisCacheService redisCacheService;

    @Override
    public CheckRoomResponse checkRoom(CheckRoomRequest checkRoomRequest){
        String roomId = checkRoomRequest.getRoomId();
        Object checkRoom = redisCacheService.hGet("room" + roomId,checkRoomRequest.getUserId());
        Boolean isValidRoom;
        if(checkRoom == null){
            isValidRoom = false;
        }
        else {
            isValidRoom = true;
        }
        CheckRoomResponse checkRoomResponse = CheckRoomResponse.builder().isValidRoom(isValidRoom).build();
        return checkRoomResponse;
    }

}
