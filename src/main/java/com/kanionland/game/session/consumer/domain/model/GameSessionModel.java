package com.kanionland.game.session.consumer.domain.model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameSessionModel {

  private UUID sessionId;
  private String username;
  private String description;
  private String character;
  private String actionType;
  private Map<String, Object> actionDetails;
  private LocalDateTime timestamp;
}
