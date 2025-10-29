package com.kanionland.game.session.consumer.application.adapters.output.persistence.documents;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "game_sessions")
public class GameSessionEntry {

  @Id
  private UUID sessionId;
  private String username;
  private String description;
  private String character;
  private String actionType;
  private Map<String, Object> actionDetails;
  private LocalDateTime timestamp;
}
