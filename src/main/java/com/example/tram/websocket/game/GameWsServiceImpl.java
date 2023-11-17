package com.example.tram.websocket.game;

import com.example.tram.config.redis.IRedisCacheService;
import com.example.tram.websocket.objects.board.Board;
import com.example.tram.websocket.objects.food.Food;
import com.example.tram.websocket.game.interface_game.IGameWsService;
import com.example.tram.websocket.game.payload.SetRoomPayload;
import com.example.tram.websocket.game.payload.WaitingRoomPayload;
import com.example.tram.websocket.objects.game.GameWs;
import com.example.tram.websocket.objects.snake.Snake;
import com.example.tram.websocket.objects.snake.enum_snake.EMoveAction;
import com.example.tram.websocket.objects.snake.enum_snake.EState;
import com.example.tram.websocket.objects.snake.enum_snake.EType;
import com.example.tram.websocket.objects.unit.Unit;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class GameWsServiceImpl implements IGameWsService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private IRedisCacheService redisCacheService;

    private Logger logger = LoggerFactory.getLogger(GameWsServiceImpl.class);


    @Override
    public void waitForGame(String sessionId, WaitingRoomPayload waitingRoomPayload){
        logger.info(sessionId + " is waiting for game");
        redisCacheService.hSet("wait-room",sessionId,waitingRoomPayload.getUserId());

        String room = "123";
        SetRoomPayload roomPayload
                = SetRoomPayload.builder().roomId(room).build();

        redisCacheService.hSet("room" + room,waitingRoomPayload.getUserId(),sessionId);
        simpMessagingTemplate.convertAndSend("/ws/game/set-room",roomPayload);
        this.startGame("123");
    }

    @Override
    public void leaveWaitingGame(String sessionId, WaitingRoomPayload waitingRoomPayload){
        logger.info(sessionId + " left the waiting room");
        redisCacheService.hDel("wait-room",sessionId);
    }

    @Override
    public void startGame(String room){
        List<Food> foods = new ArrayList<>();
        for(int i=0;i<10;i++){
            Food food = Food
                    .builder()
                    .point(1)
                    .position(new Unit(i,i+3))
                    .build();
            foods.add(food);
        }
        List<Unit> nodes = new ArrayList<>();
        nodes.add(new Unit(4,10));
        nodes.add(new Unit(5,10));
        nodes.add(new Unit(6,10));
        Snake snake = Snake
                .builder()
                .moveAction(EMoveAction.RIGHT)
                .type(EType.USER)
                .state(EState.ACTIVE)
                .nodes(nodes)
                .build();
        List<Snake> snakes = new ArrayList<>();
        snakes.add(snake);

        GameWs game = GameWs
                .builder()
                .foods(foods)
                .snakes(snakes)
                .sizeBoard(new Board(50,50,"red"))
                .ttl(120)
                .build();
        simpMessagingTemplate.convertAndSend("/ws/game/start",game);
    }
}
