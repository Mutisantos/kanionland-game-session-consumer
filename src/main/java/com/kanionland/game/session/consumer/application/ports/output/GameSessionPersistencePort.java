package com.kanionland.game.session.consumer.application.ports.output;

import com.kanionland.game.session.consumer.infrastructure.input.jms.GameSessionMessage;
import java.util.Optional;
import java.util.UUID;

public interface GameSessionPersistencePort {

  GameSessionMessage save(GameSessionMessage message);

  Optional<GameSessionMessage> findById(UUID id);

  void delete(UUID id);
}
