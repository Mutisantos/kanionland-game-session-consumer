package com.kanionland.game.session.consumer.application.services;

import com.kanionland.game.session.consumer.application.ports.output.GameSessionPersistencePort;
import com.kanionland.game.session.consumer.infrastructure.input.GameSessionService;
import com.kanionland.game.session.consumer.infrastructure.input.jms.GameSessionMessage;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameSessionServiceImpl implements GameSessionService {

  private final GameSessionPersistencePort gameSessionPersistencePort;

  @Override
  public GameSessionMessage updateSession(GameSessionMessage gameSession) {
    return gameSessionPersistencePort.save(gameSession);
  }

  @Override
  public Optional<GameSessionMessage> getSession(UUID sessionId) {
    return gameSessionPersistencePort.findById(sessionId);
  }
}
