package com.example.tram.modules.game;

import com.example.tram.modules.user_game.UserGame;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt;
    @Nullable
    private LocalDateTime endAt;

    @OneToMany(mappedBy = "game")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<UserGame> userGames = new ArrayList<>();
    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
