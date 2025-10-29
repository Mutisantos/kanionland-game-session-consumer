package com.kanionland.game.session.consumer.infrastructure.input;

import com.kanionland.game.session.consumer.domain.model.GameSessionActionModel;
import com.kanionland.game.session.consumer.domain.model.GameSessionModel;
import com.kanionland.game.session.consumer.infrastructure.input.jms.GameSessionMessage;
import java.util.UUID;

public interface GameSessionService {

  GameSessionActionModel updateSession(GameSessionMessage message);

  GameSessionModel getSession(UUID sessionId);
}
