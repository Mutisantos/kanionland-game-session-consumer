package com.kanionland.game.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@Builder
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class GameSession {
    private final UUID id;
    private final String playerId;
    private String actionDescription;
    private final LocalDateTime startTime;
    private LocalDateTime endTime;
    private GameState state;

    public GameSession(UUID id, String playerId) {
        this.id = id;
        this.playerId = playerId;
        this.startTime = LocalDateTime.now();
        this.state = GameState.STARTED;
    }

    public void endSession() {
        this.endTime = LocalDateTime.now();
        this.state = GameState.COMPLETED;
    }
}

