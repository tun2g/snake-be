package com.example.tram.modules.game.interface_game;

import com.example.tram.modules.game.request.CheckRoomRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public interface IGameController {
    @PostMapping("/check-room")
    @PreAuthorize("permitAll")
    ResponseEntity<?> checkRoom(
            @Validated
            @RequestBody CheckRoomRequest checkRoomRequest
    );
}
