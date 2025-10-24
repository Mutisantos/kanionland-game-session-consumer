package com.kanionland.game.application.ports.input.jms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kanionland.game.domain.model.GameState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameSessionMessage {
    private UUID id;
    private String playerId;
    private String actionDescription;
    private GameState state;
    private EventType eventType;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    
    public enum EventType {
        SESSION_STARTED,
        SESSION_ENDED,
        SESSION_UPDATED
    }
}
