package com.kanionland.game.application.ports.input.jms;

import com.kanionland.game.application.ports.input.GameSessionService;
import com.kanionland.game.domain.model.GameSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GameSessionMessageListener {

    private final GameSessionService gameSessionService;

    @JmsListener(destination = "${jms.queue.game-session}")
    public void receiveGameSessionMessage(@Payload GameSessionMessage message) {
        log.info("Received game session message: {}", message);
        
        switch (message.getEventType()) {
            case SESSION_STARTED:
                handleSessionStarted(message);
                break;
            case SESSION_ENDED:
                handleSessionEnded(message);
                break;
            case SESSION_UPDATED:
                handleSessionUpdated(message);
                break;
            default:
                log.warn("Received unknown event type: {}", message.getEventType());
        }
    }

    private void handleSessionStarted(GameSessionMessage message) {
        log.info("Handling SESSION_STARTED event for player: {}", message.getPlayerId());
        GameSession session = new GameSession(message.getId(), message.getPlayerId());
        gameSessionService.startSession(session);
    }

    private void handleSessionEnded(GameSessionMessage message) {
        log.info("Handling SESSION_ENDED event for session: {}", message.getId());
        gameSessionService.getSession(message.getId()).ifPresent(session -> {
            session.endSession();
            gameSessionService.endSession(session);
        });
    }

    private void handleSessionUpdated(GameSessionMessage message) {
        log.info("Handling SESSION_UPDATED event for session: {}", message.getId());
        gameSessionService.getSession(message.getId()).ifPresent(session -> {
            gameSessionService.updateSession(session);
        });
    }
}
