package com.kanionland.game.session.consumer.application.adapters.output.persistence.documents;

import java.time.LocalDateTime;
import java.util.List;
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
public class GameSessionDocument {

  @Id
  private UUID sessionId;
  private List<GameSessionEntry> messages;
  private LocalDateTime createdAt;
  private LocalDateTime lastUpdated;

  public void pushMessage(GameSessionEntry entry) {
    this.messages.add(entry);
  }
}
