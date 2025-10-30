package com.kanionland.game.session.consumer.infrastructure.input.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.kanionland.game.session.consumer.infrastructure.input.GameSessionService;
import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieSession;
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
  private final KieSession kieSession;

  @JmsListener(destination = "${jms.queue.game-session}")
  public void receiveGameSessionMessage(@Payload Object message) {
    GameSessionMessage parsedMessage = null;
    KieSession session = null;
    
    try {
      // Parse the message
      String messageContent = ((TextMessage) message).getText();
      log.debug("Raw message content: {}", messageContent);
      parsedMessage = xmlMapper.readValue(messageContent, GameSessionMessage.class);
      log.info("Received action message {} for session: {}",
          parsedMessage.getActionType(),
          parsedMessage.getSessionId());

      // Create a new session for this message
      session = kieSession.getKieBase().newKieSession();
      
      try {
        // Insert the message and fire rules
        session.insert(parsedMessage);
        int rulesFired = session.fireAllRules();
        log.debug("Applied {} rules to message with action type: {}", 
            rulesFired, parsedMessage.getActionType());
            
        // Process the message with the updated data
        gameSessionService.updateSession(parsedMessage);
      } finally {
        // Always dispose the session to prevent memory leaks
        if (session != null) {
          session.dispose();
        }
      }
      
    } catch (Exception e) {
      log.error("Error processing message: {}", e.getMessage(), e);
      throw new RuntimeException("Failed to process message", e);
    }
  }

}
