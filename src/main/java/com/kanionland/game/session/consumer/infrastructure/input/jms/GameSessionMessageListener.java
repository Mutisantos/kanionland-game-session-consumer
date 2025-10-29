package com.kanionland.game.session.consumer.infrastructure.input.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.kanionland.game.session.consumer.infrastructure.input.GameSessionService;
import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GameSessionMessageListener {

  @Qualifier("xmlMapper")
  private final XmlMapper xmlMapper;
  private final GameSessionService gameSessionService;

  @JmsListener(destination = "${jms.queue.game-session}")
  public void receiveGameSessionMessage(@Payload Object message) {
    GameSessionMessage parsedMessage = null;
    try {
      String messageContent = ((TextMessage) message).getText();
      log.debug("Raw message content: {}", messageContent);
      parsedMessage = xmlMapper.readValue(messageContent, GameSessionMessage.class);
      log.info("Received action message {} for session: {}",
          parsedMessage.getActionType(),
          parsedMessage.getSessionId());
      gameSessionService.updateSession(parsedMessage);
    } catch (JsonProcessingException | JMSException e) {
      log.error("Error processing message: {}", e);
      throw new RuntimeException(e);
    }
  }

}
