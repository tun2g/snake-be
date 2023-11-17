package com.example.tram.websocket.objects.game;

import com.example.tram.websocket.objects.board.Board;
import com.example.tram.websocket.objects.food.Food;
import com.example.tram.websocket.objects.snake.Snake;
import com.example.tram.websocket.objects.unit.Unit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class GameWs {
    private List<Snake> snakes;

    private List<Food> foods;

    private Board sizeBoard;

    private int ttl;
}
