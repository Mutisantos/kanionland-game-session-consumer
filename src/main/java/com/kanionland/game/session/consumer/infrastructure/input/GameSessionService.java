package com.kanionland.game.session.consumer.infrastructure.input;

import com.kanionland.game.session.consumer.infrastructure.input.jms.GameSessionMessage;
import java.util.Optional;
import java.util.UUID;

public interface GameSessionService {

  GameSessionMessage updateSession(GameSessionMessage message);

  Optional<GameSessionMessage> getSession(UUID sessionId);
}
