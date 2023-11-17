package com.example.tram.websocket.objects.unit;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Unit {
    private int posX;
    private int posY;
}
