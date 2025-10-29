package com.kanionland.game.session.consumer.domain.model;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameSessionActionModel {

  private String messageId;
  private String username;
  private String description;
  private String character;
  private String actionType;
  private Map<String, Object> actionDetails;
  private LocalDateTime timestamp;
}
