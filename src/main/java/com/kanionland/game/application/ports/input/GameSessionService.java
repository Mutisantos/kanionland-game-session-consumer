package com.kanionland.game.application.ports.input;

import com.kanionland.game.application.ports.input.jms.GameSessionMessage;
import com.kanionland.game.domain.model.GameSession;

import java.util.Optional;
import java.util.UUID;

public interface GameSessionService {
    GameSession startSession(GameSession message);
    GameSession endSession(GameSession message);
    GameSession updateSession(GameSession message);
    Optional<GameSession> getSession(UUID sessionId);
}
