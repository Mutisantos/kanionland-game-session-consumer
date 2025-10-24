package com.kanionland.game.application.adapters.output.persistence.documents;

import com.kanionland.game.domain.model.GameSession;
import com.kanionland.game.domain.model.GameState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "game_sessions")
public class GameSessionEntry {

  @Id
  private UUID id;
  private String playerId;
  private String actionDescription;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private GameState state;

  public static GameSessionEntry fromDomain(GameSession gameSession) {
    return GameSessionEntry.builder()
        .id(gameSession.getId())
        .playerId(gameSession.getPlayerId())
        .actionDescription(gameSession.getActionDescription())
        .startTime(gameSession.getStartTime())
        .endTime(gameSession.getEndTime())
        .state(gameSession.getState())
        .build();
  }
}
