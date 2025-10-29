package com.kanionland.game.session.consumer.infrastructure.input.jms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
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
@JacksonXmlRootElement(localName = "GameSessionMessage")
public class GameSessionMessage {

  @JacksonXmlProperty(localName = "sessionId")
  private UUID sessionId;
  @JacksonXmlProperty(localName = "username")
  private String username;
  @JacksonXmlProperty(localName = "description")
  private String description;
  @JacksonXmlProperty(localName = "character")
  private String character;
  @JacksonXmlProperty(localName = "actionType")
  private String actionType;
  @JacksonXmlProperty(localName = "actionDetails")
  private Map<String, Object> actionDetails;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  @JacksonXmlProperty(localName = "timestamp")
  private LocalDateTime timestamp;
}
