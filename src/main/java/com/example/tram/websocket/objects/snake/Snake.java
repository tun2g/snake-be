package com.example.tram.websocket.objects.snake;

import com.example.tram.websocket.objects.snake.enum_snake.EState;
import com.example.tram.websocket.objects.snake.enum_snake.EType;
import com.example.tram.websocket.objects.snake.enum_snake.EMoveAction;
import com.example.tram.websocket.objects.unit.Unit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Snake {
    List<Unit> nodes;

    EType type;

    EState state;

    String color;

    EMoveAction moveAction;

    public Unit getHead(){
        return this.nodes.get(0);
    }

    private void removeTail(){
        this.nodes.remove(this.nodes.size()-1);
    }

    public void onMovingLeft(){
        if(this.moveAction != EMoveAction.LEFT){
            this.moveAction = EMoveAction.LEFT;
            this.removeTail();
            Unit newNode = Unit.builder()
                    .posX(this.getHead().getPosX()-1)
                    .posY(this.getHead().getPosY())
                    .build();
            this.nodes.add(newNode);
        }
    }

    public void onMovingRight(){
        if(this.moveAction != EMoveAction.RIGHT){
            this.moveAction = EMoveAction.RIGHT;
            this.removeTail();
            Unit newNode = Unit.builder()
                    .posX(this.getHead().getPosX()+1)
                    .posY(this.getHead().getPosY())
                    .build();
            this.nodes.add(newNode);
        }
    }

    public void onMovingTop(){
        if(this.moveAction != EMoveAction.TOP){
            this.moveAction = EMoveAction.TOP;
            this.removeTail();
            Unit newNode = Unit.builder()
                    .posX(this.getHead().getPosX())
                    .posY(this.getHead().getPosY()-1)
                    .build();
            this.nodes.add(newNode);
        }
    }

    public void onMovingBottom(){
        if(this.moveAction != EMoveAction.BOTTOM){
            this.moveAction = EMoveAction.BOTTOM;
            this.removeTail();
            Unit newNode = Unit.builder()
                    .posX(this.getHead().getPosX())
                    .posY(this.getHead().getPosY()+1)
                    .build();
            this.nodes.add(newNode);
        }
    }

}
