package com.kanionland.game.application.ports.output;

import com.kanionland.game.domain.model.GameSession;

import java.util.Optional;
import java.util.UUID;

public interface GameSessionPersistencePort {
    GameSession save(GameSession gameSession);
    Optional<GameSession> findById(UUID id);
    void delete(UUID id);
}
