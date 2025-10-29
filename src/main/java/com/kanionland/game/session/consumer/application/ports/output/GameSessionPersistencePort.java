package com.kanionland.game.session.consumer.application.ports.output;

import com.kanionland.game.session.consumer.domain.model.GameSessionActionModel;
import com.kanionland.game.session.consumer.domain.model.GameSessionModel;
import com.kanionland.game.session.consumer.infrastructure.input.jms.GameSessionMessage;
import java.util.UUID;

public interface GameSessionPersistencePort {

  GameSessionActionModel save(GameSessionMessage message);

  GameSessionModel findById(UUID id);

  void delete(UUID id);
}
