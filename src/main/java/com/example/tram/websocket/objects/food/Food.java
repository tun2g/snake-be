package com.example.tram.websocket.objects.food;

import com.example.tram.websocket.objects.unit.Unit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    private Unit position;
    private int point;
}
